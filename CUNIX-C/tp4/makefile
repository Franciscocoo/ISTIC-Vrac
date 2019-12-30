CFLAGS = -Wall -g

#
# Link
#

bin/test_list: obj/test_list.o obj/list.o
	gcc -o bin/test_list obj/test_list.o obj/list.o

#
# Objets of TP Lists
#

obj/list.o: src/list.c include/list.h
	gcc $(CFLAGS) -c src/list.c -o obj/list.o

obj/test_list.o: src/test_list.c include/list.h
	gcc $(CFLAGS) -c src/test_list.c -o obj/test_list.o 

#
# Listing creation in pdf
#

listing: 
	a2ps -o listing.ps include/list.h src/list.c src/test_list.c
	ps2pdf listing.ps
	rm listing.ps

#
## Remove files
#
#
clean :
	rm obj/list.o
	rm bin/test_list

#
# Test the functionality 
#

test :
	./bin/test_list
