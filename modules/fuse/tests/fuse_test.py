#!/usr/env/env python3
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
import os
import time
from enum import Enum
from logging import fatal

import numpy as np
import pandas as pd
import pyarrow as pa

import matplotlib.pyplot as plt
import psutil


class Type(Enum):
    STRING = 1
    INT64 = 2
    DOUBLE = 3


def generate_dataframe(size=(3, 4)):
    height, width = size
    ldf = pd.DataFrame(
        np.random.randint(0, 100, size=(height, width)) * 2.3,
        columns=[''.join(['a'] * i) for i in range(1, width + 1)],
    )
    rdf = pd.DataFrame(
        np.random.randint(0, 100, size=(height, width)),
        columns=[''.join(['b'] * i) for i in range(1, width + 1)],
    )
    return pd.concat([ldf, rdf], axis=1, join="inner")


def generate_string_array(length=20):
    res = []
    alphabet = [
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z',
        ' ',
    ]

    for _ in range(1, length):
        s_length = np.random.randint(1, length)
        res.append(''.join(np.random.choice(alphabet, s_length)))

    return res


def generate_array(type: Type, length=20):
    f = {
        Type.INT64: lambda x: np.random.randint(0, 1000, x),
        Type.DOUBLE: lambda x: np.random.uniform(low=0, high=1000, size=x),
        Type.STRING: generate_string_array,
    }
    return pa.array(f[type](length))


def assert_dataframe(stored_df: pd.DataFrame, extracted_df: pa.Table):
    pdf = pa.Table.from_pandas(stored_df)
    assert extracted_df.equals(pdf), "data frame unmatch"


def assert_array(stored_arr: pa.Array, extracted_array: pa.Array):
    assert stored_arr.equals(extracted_array), "array unmatch"


def read_data_from_fuse(vid, test_mount_dir):
    with open(os.path.join(test_mount_dir, vid), 'rb') as source:
        with pa.ipc.open_file(source) as reader:
            data = reader.read_all()
            return data


def compare_two_string_array(arr_str_1, arr_str_2):
    a = arr_str_1
    b = arr_str_2
    if len(a) != len(b):
        return False
    else:
        for i, j in zip(a, b):
            if str(i) != str(j):
                return False
    return True


# def test_fuse_int64_array(vineyard_client, vineyard_fuse_mount_dir):
#     data = generate_array(Type.INT64)
#     id = vineyard_client.put(data)
#     extracted_data = read_data_from_fuse(
#         str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir
#     )

#     extracted_data = extracted_data.column("a").chunk(0)
#     assert_array(data, extracted_data)


# def test_fuse_double_array(vineyard_client, vineyard_fuse_mount_dir):
#     data = generate_array(Type.DOUBLE)
#     id = vineyard_client.put(data)
#     extracted_data = read_data_from_fuse(
#         str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir
#     )

#     extracted_data = extracted_data.column("a").chunk(0)
#     assert_array(data, extracted_data)


# def test_fuse_string_array(vineyard_client, vineyard_fuse_mount_dir):
#     data = generate_array(Type.STRING)
#     id = vineyard_client.put(data)
#     extracted_data = read_data_from_fuse(
#         str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir
#     )
#     extracted_data = extracted_data.column("a").chunk(0)
#     assert compare_two_string_array(data, extracted_data), "string array not the same"


# def test_fuse_df(vineyard_client, vineyard_fuse_mount_dir):
#     data = generate_dataframe()

#     id = vineyard_client.put(data)
#     extracted_data = read_data_from_fuse(
#         str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir
#     )
#     assert_dataframe(data, extracted_data)


def test_cache_manager_memory_usage_generation(
    vineyard_client,
    vineyard_fuse_mount_dir,
    vineyard_fuse_process_pid,
    vineyard_fuse_max_cache_size,
):
    data = generate_dataframe((20, 4))
    pid = int(vineyard_fuse_process_pid)
    print("process_id", pid)
    stats = []
    data_size = 0

    for _ in range(1, 80):

        id = vineyard_client.put(data)
        rdata = read_data_from_fuse(str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir)
        data_size += rdata.nbytes

        stats.append([data_size, psutil.Process(pid).memory_info().rss])
    stats = np.array(stats)
    plt.style.use('seaborn')
    fig, ax = plt.subplots()
    ax.plot(stats[:, 0], stats[:, 1])

    ax.plot(
        [int(vineyard_fuse_max_cache_size), int(vineyard_fuse_max_cache_size)],
        [min(stats[:, 1]), max(stats[:, 1])],
        color='r',
    )
    ax.set_xlabel("mount of data cached or caching")
    ax.set_ylabel("memory usage")
    ax.ticklabel_format(style='plain')
    # print("!!!!!!!!!",os.getcwd(),vineyard_fuse_max_cache_size)
    fig.savefig(os.path.join(os.getcwd(), 'memory_measurement.png'))


# def test_cache_manager(
#     vineyard_client, vineyard_fuse_mount_dir, vineyard_fuse_process_pid
# ):
#     data = generate_dataframe((20, 4))
#     pid = int(vineyard_fuse_process_pid)
#     print("process_id", pid)
#     stats = []
#     for _ in range(1, 100):

#         id = vineyard_client.put(data)
#         _ = read_data_from_fuse(str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir)
#         stats.append(psutil.Process(pid).memory_info().rss)

#     memory_usage_before = psutil.Process(pid).memory_info().rss / 1024**2
#     data = generate_dataframe((20, 4))

#     for _ in range(1, 300):

#         id = vineyard_client.put(data)
#         _ = read_data_from_fuse(str(id)[11:28] + ".arrow", vineyard_fuse_mount_dir)
#         stats.append(psutil.Process(pid).memory_info().rss)

#     memory_usage_after = psutil.Process(pid).memory_info().rss / 1024**2
#     if abs((memory_usage_before) - (memory_usage_after)) > 0.0001:
#         fatal("memory usage changed")
#     for s in stats:
#         print(s)
