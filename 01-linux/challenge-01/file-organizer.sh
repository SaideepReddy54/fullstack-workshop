#!/bin/bash
set -e #Exit on error
set -u #Exit on undefined Variable
set -o pipefail #catch errors in pipes
DIR=$1


if [ -z "$DIR" ] || [ ! -d "$DIR" ]; then
    echo "Usage: ./file-organizer.sh /C:/Users/saide"
    exit 1
fi

cd "$DIR" || exit

declare -A count

for file in *; do

    [ -f "$file" ] || continue


    if [[ "$file" == *.* ]]; then
        ext="${file##*.}"
    else
        ext="others"
    fi

    mkdir -p "$ext"

    mv "$file" "$ext/"

    count[$ext]=$((count[$ext] + 1))
done


echo "----- Summary -----"
for ext in "${!count[@]}"; do
    echo "Organized ${count[$ext]} .$ext files"
done