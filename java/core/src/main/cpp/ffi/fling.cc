/**
 * NOLINT(legal/copyright)
 *
 * The file src/common/memory/fling.cc is referred and derived from project
 * flingfd,
 *
 *    https://github.com/sharvil/flingfd/blob/master/src/flingfd.c
 *
 * which has the following license:
 *
// Copyright 2013 Sharvil Nanavati
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
 */

#include "./fling.h"

#include <errno.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/uio.h>
#include <unistd.h>

void init_msg(struct msghdr* msg, struct iovec* iov, char* buf,
              size_t buf_len) {
  iov->iov_base = buf;
  iov->iov_len = 1;

  msg->msg_iov = iov;
  msg->msg_iovlen = 1;
  msg->msg_control = buf;
  msg->msg_controllen = static_cast<socklen_t>(buf_len);
  msg->msg_name = NULL;
  msg->msg_namelen = 0;
}

int send_fd(int conn, int fd) {
  struct msghdr msg;
  struct iovec iov;
  char buf[CMSG_SPACE(sizeof(int))];
  memset(&buf, 0, CMSG_SPACE(sizeof(int)));

  init_msg(&msg, &iov, buf, sizeof(buf));

  struct cmsghdr* header = CMSG_FIRSTHDR(&msg);
  if (header == nullptr) {
    fprintf(stderr, "Error in init_msg: header is NULL\n");
    return -1;
  }
  header->cmsg_level = SOL_SOCKET;
  header->cmsg_type = SCM_RIGHTS;
  header->cmsg_len = CMSG_LEN(sizeof(int));
  memcpy(CMSG_DATA(header), reinterpret_cast<void*>(&fd), sizeof(int));

  // Send file descriptor.
  while (true) {
    ssize_t r = sendmsg(conn, &msg, 0);
    if (r < 0) {
      if (errno == EAGAIN || errno == EWOULDBLOCK || errno == EINTR) {
        fprintf(stderr, "error occurred while looping in sending fd: %s\n", strerror(errno));
        continue;
      } else if (errno == EMSGSIZE) {
        fprintf(stderr, "Failed to send file descriptor (errno = EMSGSIZE, %s), retrying...\n", strerror(errno));
        // If we failed to send the file descriptor, loop until we have sent it
        // successfully. TODO(rkn): This is problematic for two reasons. First
        // of all, sending the file descriptor should just succeed without any
        // errors, but sometimes I see a "Message too long" error number.
        // Second, looping like this allows a client to potentially block the
        // bulk store event loop which should never happen.
        continue;
      } else {
        fprintf(stderr, "Error in send_fd (errno = %d: %s\n", errno, strerror(errno));
        return static_cast<int>(r);
      }
    } else if (r == 0) {
      fprintf(stderr, "Encountered unexpected EOF");
      return 0;
    } else {
      return static_cast<int>(r);
    }
  }
}

int recv_fd(int conn) {
  struct msghdr msg;
  struct iovec iov;
  char buf[CMSG_SPACE(sizeof(int))];
  init_msg(&msg, &iov, buf, sizeof(buf));

  while (true) {
    ssize_t r = recvmsg(conn, &msg, 0);
    if (r == -1) {
      if (errno == EAGAIN || errno == EWOULDBLOCK || errno == EINTR) {
        continue;
      } else {
        fprintf(stderr, "Error in recv_fd (errno = %d)\n", errno);
        return -1;
      }
    } else {
      break;
    }
  }

  int found_fd = -1;
  int oh_noes = 0;
  for (struct cmsghdr* header = CMSG_FIRSTHDR(&msg); header != NULL;
       header = CMSG_NXTHDR(&msg, header))
    if (header->cmsg_level == SOL_SOCKET && header->cmsg_type == SCM_RIGHTS) {
      ssize_t count =
          (header->cmsg_len -
           (CMSG_DATA(header) - reinterpret_cast<unsigned char*>(header))) /
          sizeof(int);
      for (int i = 0; i < count; ++i) {
        int fd = (reinterpret_cast<int*>(CMSG_DATA(header)))[i];
        if (found_fd == -1) {
          found_fd = fd;
        } else {
          close(fd);
          oh_noes = 1;
        }
      }
    }

  // The sender sent us more than one file descriptor. We've closed
  // them all to prevent fd leaks but notify the caller that we got
  // a bad message.
  if (oh_noes) {
    close(found_fd);
    errno = EBADMSG;
    fprintf(stderr, "Error in recv_fd: more than one fd received in message");
    return -1;
  }

  return found_fd;
}
