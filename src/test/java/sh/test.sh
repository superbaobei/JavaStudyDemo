#!/bin/bash
path=~/Downloads/

if [ -d $path ]
then
    echo "进入文件夹$path"
    cd $path
else
    echo "$path 存在但是不是一个文件夹"
fi

for i in test*
do
    a=$i
    echo "file name : $(basename $a)"
    echo "a : $a"
    echo "dir : $(dirname $a)"
    echo "a : $a"
    if [ -d $i ]
    then
        echo "$i is dir"
    else
        echo "$i is not dir"
    fi
done
var=123456
echo ${var:0:5}
dirs=/Users/xiangyusun/Downloads/testDir
cd $dirs
for i in *
do
    if [[ $i != test* ]]
    then
        rm -f $i
    else
        echo $i false
    fi
done