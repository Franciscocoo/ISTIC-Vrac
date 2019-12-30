#!/bin/bash
if [ -f $1 ];then
	read i;
	while
		[ $i != 0 ]
	do
		echo  $i |cat - $1| ./prog2;
		read i;
	done
else
	echo "Fichier inexistant";
fi
