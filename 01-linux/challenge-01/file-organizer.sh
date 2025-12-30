 #!/bin/bash

# Exit on error, undefined variable, and pipe failure
set -e
set -u
set -o pipefail

# Directory passed as first argument (safe with set -u)
DIR="${1:-}"

# Validate input directory
if [ -z "$DIR" ] || [ ! -d "$DIR" ]; then
     echo "Usage: ./file-organizer.sh /C:/Users/saide"
    exit 1
fi

# Move into the target directory
cd "$DIR"

# Associative array to count files by extension
declare -A count

# Iterate over files in directory
for file in *; do
    [ -f "$file" ] || continue

    # Determine file extension
    if [[ "$file" == *.* ]]; then
        ext="${file##*.}"
    else
        ext="others"
    fi

    # Create directory for extension if it doesn't exist
    mkdir -p "$ext"

    # Move file into its extension folder
    mv "$file" "$ext/"

    # Update count
    count["$ext"]=$((count["$ext"] + 1))
done

# Print summary
echo "----- Summary -----"
for ext in "${!count[@]}"; do
    echo "Organized ${count[$ext]} .$ext files"
done