#!/bin/bash
var=$(grep $1 $2);
if [ -n $var ]; then
	echo "Filename got motif";
else 
	echo "Filename no got motif";
fi
