#!/bin/bash
if [ -f $2 ];then
	echo $1 |cat - $2| ./prog2 > $3;
else
	echo "Fichier non existant";
fi
