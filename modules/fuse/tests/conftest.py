#! /usr/bin/env python
# -*- coding: utf-8 -*-
#
# Copyright 2020-2022 Alibaba Group Holding Limited.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

import logging

import matplotlib.pyplot as plt
import pytest

import vineyard

logging.basicConfig(level=logging.CRITICAL)


def pytest_configure():
    logger = logging.getLogger('matplotlib')
    logger.disabled = True
    plt.set_loglevel("error")


def pytest_addoption(parser):
    parser.addoption(
        '--vineyard-ipc-socket',
        action='store',
        default='/tmp/vineyard.sock',
        help='Location of vineyard IPC socket',
    )

    parser.addoption(
        '--vineyard-endpoint',
        action='store',
        default='127.0.0.1:9600',
        help='Address of vineyard RPC endpoint',
    )

    parser.addoption(
        '--vineyard-fuse-mount-dir',
        action='store',
        default='/tmp/vineyard_fuse.default',
        help='fusermount directory',
    )
    parser.addoption(
        '--vineyard-fuse-process-pid',
        action='store',
        default=None,
        help='fuse process id',
    )
    parser.addoption(
        '--vineyard-fuse-max-cache-size',
        action='store',
        default=None,
        help='fuse max cache size managed by cache manager',
    )


@pytest.fixture(scope='session')
def vineyard_fuse_max_cache_size(request):
    return request.config.option.vineyard_fuse_max_cache_size


@pytest.fixture(scope='session')
def vineyard_fuse_process_pid(request):
    return request.config.option.vineyard_fuse_process_pid


@pytest.fixture(scope='session')
def vineyard_ipc_socket(request):
    return request.config.option.vineyard_ipc_socket


@pytest.fixture(scope='session')
def vineyard_endpoint(request):
    return request.config.option.vineyard_endpoint


@pytest.fixture(scope='session')
def vineyard_fuse_mount_dir(request):
    return request.config.option.vineyard_fuse_mount_dir


@pytest.fixture(scope='session')
def vineyard_client(request):
    ipc_socket = request.config.option.vineyard_ipc_socket
    return vineyard.connect(ipc_socket)
