#!/bin/bash
# Disk usage monitoring script

set -e          # Exit on error
set -u          # Exit on undefined variable
set -o pipefail # Catch errors in pipes

# Default threshold is 90 if not provided
THRESHOLD="${1:-90}"
ALERT=0

# Validate argument
if [ "$THRESHOLD" -lt 1 ] || [ "$THRESHOLD" -gt 100 ]; then
    echo "Usage: $0 [THRESHOLD_PERCENT]"
    exit 1
fi

# Read df output safely without subshell
while read -r filesystem size used avail use mount; do
    usage="${use%\%}"

    if [ "$usage" -gt "$THRESHOLD" ]; then
        echo "WARNING: $filesystem is at ${usage}% (threshold: ${THRESHOLD}%)"
        ALERT=1
    else
        echo "OK: $filesystem is at ${usage}%"
    fi
done < <(df -h | tail -n +2)

# Exit status based on alert
if [ "$ALERT" -eq 1 ]; then
    exit 1
else
    exit 0
fi