#!/bin/bash
echo "$# arguments : ";
i=0
for var in $@
do
	((i++));	
	echo "Argument $i  -  " expr $var
done
