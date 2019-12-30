#include <stdio.h>
#include <string.h>
char* mess="hello world\n";
int main() {
	strcpy(mess+4,mess) ;
	return 0;
}
