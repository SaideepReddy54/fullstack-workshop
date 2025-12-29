#!/bin/bash
set -e #Exit on error
set -u #Exit on undefined Variable
set -o pipefail #catch errors in pipes
PROCESS=$1
INTERVAL=$2

if [ -z "$PROCESS" ] || [ -z "$INTERVAL" ]; then
    echo "Usage: ./process-monitor.sh nginx 5"
    exit 1
fi

if ! ps -ef | grep "$PROCESS" | grep -v grep > /dev/null; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Process '$PROCESS' is not running."
    exit 1
fi

echo "Monitoring process '$PROCESS' every $INTERVAL seconds..."


while true; do
    TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

    if ps -ef | grep "$PROCESS" | grep -v grep > /dev/null; then
        echo "$TIMESTAMP - $PROCESS is running"
    else
        echo "$TIMESTAMP - ALERT: $PROCESS has stopped!"
        exit 0
    fi

    sleep "$INTERVAL"
done