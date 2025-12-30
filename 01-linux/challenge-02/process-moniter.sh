#!/bin/bash
# Process monitoring script

set -e          # Exit immediately on command failure
set -u          # Treat unset variables as an error
set -o pipefail # Catch errors in pipelines

# Read command-line arguments safely
PROCESS="${1:-}"
INTERVAL="${2:-}"

# Validate inputs
if [ -z "$PROCESS" ] || [ -z "$INTERVAL" ]; then
    echo "Usage: ./process-monitor.sh nginx 5"
    exit 1
fi

# Check if process exists before monitoring
if ! ps -ef | grep -w "$PROCESS" | grep -v grep > /dev/null; then
    echo "$(date '+%Y-%m-%d %H:%M:%S') - Process '$PROCESS' is not running."
    exit 1
fi

echo "Monitoring process '$PROCESS' every $INTERVAL seconds..."

# Continuous monitoring loop
while true; do
    TIMESTAMP=$(date "+%Y-%m-%d %H:%M:%S")

    if ps -ef | grep -w "$PROCESS" | grep -v grep > /dev/null; then
        echo "$TIMESTAMP - $PROCESS is running"
    else
        echo "$TIMESTAMP - ALERT: $PROCESS has stopped!"
        exit 0
    fi

    sleep "$INTERVAL"
done