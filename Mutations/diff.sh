#!/usr/bin/env bash
for j in {0..9}
do
    echo " ============== $j =================="
    for file in {d_$j/st/a.java,d_$j/st/TemplateEngine.java}
        do
        echo "diff "$file" "d_0/st/${file##*/}""
        diff "$file" "d_0/st/${file##*/}"
        done
done