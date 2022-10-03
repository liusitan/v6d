#!/bin/sh
# this file is supposed to be run on the raw file
mkdir -p build
cd build
cmake .. -DBUILD_VINEYARD_GRAPH=OFF -DBUILD_VINEYARD_FUSE=ON 
make vineyard-fusermount
make vineyardd
../test/runner.py --with-fuse --tests test_cache_manager_memory_usage_generation
# check the memory_measurement.png under root directory