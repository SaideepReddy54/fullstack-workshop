THRESHOLD=${1:-90}
ALERT=0

df -h | tail -n +2 | while read filesystem size used avail use mount; do
    usage=${use%\%}

    if [ "$usage" -gt "$THRESHOLD" ]; then
        echo "WARNING: $filesystem is at ${usage}% (threshold: ${THRESHOLD}%)"
        ALERT=1
    else
        echo "OK: $filesystem is at ${usage}%"
    fi
done


if [ "$ALERT" -eq 1 ]; then
    exit 1
else
    exit 0
fi