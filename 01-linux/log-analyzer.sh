#!/bin/bash

# Exit on error, undefined variable, or pipe failure
set -e
set -u
set -o pipefail

# Check if file path argument is provided
if [ $# -ne 1 ]; then
    echo "Usage: $0 <log-file-path>"
    exit 1
fi

# Log file path from command-line argument
path="$1"

# Check if file exists
if [ -f "$path" ]; then

    echo "=== Log Analysis Report ==="
    echo "File: $path"
    echo "Total Entries: $(wc -l < "$path")"
    echo

    echo "Log Level Summary:"
    printf "  ERROR: %d\n" "$(grep -ci 'error' "$path")"
    printf "  WARN : %d\n" "$(grep -ci 'warning' "$path")"
    printf "  INFO : %d\n" "$(grep -ci 'info' "$path")"
    echo

    echo "Top 5 Error Messages:"
    grep -i 'error' "$path" \
        | sed -E 's/^.*ERROR[: ]*//' \
        | sort \
        | uniq -c \
        | sort -nr \
        | head -5 \
        | awk '{printf "  %d. %s (%d occurrences)\n", NR, substr($0, index($0,$2)), $1}'
    echo

    echo "Unique IP Addresses:"
    grep -oE '[0-9]{1,3}(\.[0-9]{1,3}){3}' "$path" \
        | sort -u \
        | awk '{print "  - " $0}'

else
    echo "File does not exist: $path"
    exit 1
fi