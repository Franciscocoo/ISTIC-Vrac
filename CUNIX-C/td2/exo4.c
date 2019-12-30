#include <stdio.h>
#include <string.h>
int reverse(char* str) {
	int j = 0;
	int size = strlen(str); 
	j=size-1;
	for(int i=0;i<(size)/2;i++){
		//printf("sizeof string : %d \n", size);
		//printf("swap i at %d : %c \n",i, str[i]);
		//printf("swap j at %d : %c \n",j, str[j]);
		char temp=str[i];
		str[i]=str[j];
		str[j]=temp;
		j--;
        }

return sizeof(str);	
}

int main (int argc, char **argv[]) {
	char str[]="Hello World.";
	reverse(str);
	printf("%s",str);
	return 0;

}
