CLAGS = -Wall -g

#
# Link
#

bin/main : obj/main.o obj/file.o obj/hash.o
	gcc -o bin/main obj/main.o obj/file.o obj/hash.o	

#
# Objets of TP Lists
#

obj/file.o : src/file.c include/functions.h include/types.h
	gcc $(CFLAGS) -c src/file.c -o obj/file.o

obj/hash.o : src/hash.c include/functions.h
	gcc $(CFLAGS) -c src/hash.c -o obj/hash.o

obj/main.o : src/main.c src/file.c src/hash.c include/functions.h include/types.h
	gcc $(CFLAGS) -c src/main.c -o obj/main.o

#
# Listing creation in pdf
#

listing: 
	a2ps -o listing.ps include/functions.h include/types.c src/main.c src/file.c src/main.c
	ps2pdf listing.ps
	rm listing.ps

#
## Remove files
#
#
clean :
	rm obj/file.o
	rm obj/hash.o
	rm bin/main

#
# Test the functionality 
#

test :
	./bin/main
