#!/bin/bash

find build -name libnet.so -delete

find build/linux-x86_64-normal-server-fastdebug/ -name libnet | xargs -n 1 rm -r -v

make install JOBS=8

