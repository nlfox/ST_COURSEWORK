#!/usr/bin/env bash
for j in {0..9}
do
    for file in d_$j/st/*.java
        do
        echo "diff "$file" "d_0/st/${file##*/}""
        diff "$file" "d_0/st/${file##*/}"
        done
done