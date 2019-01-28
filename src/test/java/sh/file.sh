#!/bin/bash
dir=/Users/xiangyusun/Downloads
if [ -d $dir ]
then
    echo $dir 'exists'
else
    echo $dir 'not exists'
    return
fi
cd $dir
files=$(ls $dir)
#echo $files
#for i in $files
for i in *.txt
do
    #echo $i
    metadata=$(file $i)
    echo $metadata
done
temp=testFile
echo "$temp ate"