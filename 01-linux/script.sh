#!/bin/bash
set -e #Exit on error
set -u #Exit on undefined Variable
set -o pipefail #catch errors in pipes
echo "Hello,Full Stack Developer!"
mkdir logs
touch logs/app.log
date > logs/app.log
