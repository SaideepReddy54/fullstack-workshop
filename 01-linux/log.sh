LOG_FILE="/c/Users/saide/devTraining/sample-log.txt"


if [ ! -f "$LOG_FILE" ]; then
  echo "Error: File '$LOG_FILE' does not exist."
  exit 1
fi

TOTAL_LINES=$(wc -l < "$LOG_FILE")

INFO_COUNT=$(grep -c "INFO" "$LOG_FILE" 2>/dev/null)
WARNING_COUNT=$(grep -c "WARNING" "$LOG_FILE" 2>/dev/null)
ERROR_COUNT=$(grep -c "ERROR" "$LOG_FILE" 2>/dev/null)


UNIQUE_IPS=$(grep -Eo '([0-9]{1,3}\.){3}[0-9]{1,3}' "$LOG_FILE" | sort -u)


echo "========== LOG ANALYSIS REPORT =========="
echo "File: $LOG_FILE"
echo "Total Lines: $TOTAL_LINES"
echo "-----------------------------------------"
printf "INFO:    %s\n" "$INFO_COUNT"
printf "WARNING: %s\n" "$WARNING_COUNT"
printf "ERROR:   %s\n" "$ERROR_COUNT"
echo "-----------------------------------------"
echo "Unique IP Addresses Found:"

if [ -n "$UNIQUE_IPS" ]; then
  while IFS= read -r ip; do
    echo "  - $ip"
  done <<< "$UNIQUE_IPS"
else
  echo "  (none)"
fi

echo "========================================="
