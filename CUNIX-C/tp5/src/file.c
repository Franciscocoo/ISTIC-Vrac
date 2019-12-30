#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <string.h>

#include "../include/types.h"
#include "../include/functions.h" // extern functions declarations

// ------------------------------------------------------------------------
// inner functions declarations
// ------------------------------------------------------------------------


//Fct qui boucle pour tout les mots
int
add_listwords(char filename[],
	 listfile_entry * filelist,
	 hash_table * htable_ptr,
	 int fileindex);

//Fct qui rajoute un mot
int
add_word(char word[],
	int fileindex,
	hash_table * htable_ptr);

void
delete_in_hash(int fileindex,
		hash_table * htable_ptr);
//------------------------------------------------------------------------
// global functions definitions
//------------------------------------------------------------------------

/**
   Create and initialize file table of capacity maxfiles

   parameters :
   maxfiles : capacity of file table

   returns : pointer to table or NULL in case of error
 */
listfile_entry *
create_filelist(int maxfiles)
{
  listfile_entry* list_file;
  list_file = (listfile_entry*) malloc (sizeof(listfile_entry) * maxfiles);
  for(int i=0;i<maxfiles;i++){  	
	list_file[i].filename[0]='\0';
	list_file[i].loaded = 0;
  }
  return list_file;
}

/**
   add words from file to table
   - checks if the file has already been loaded
   - updates the file table (if file not already loaded)
   - reads every word in the file (idem)
   - updates the hash table (idem)

   parameters :
   filename   : name of file :)
   filelist   : pointer to table of files
   htable_ptr : pointer to hash table

   returns :
    1 if file already present in table
    2 if no space left in filelist
   -1 if file doesn't exist or can't be read
   -2 if allocation error
    0 if everything ok
*/
int
add_file(char filename[],
	 listfile_entry * filelist,
	 hash_table * htable_ptr)
{
	if(access(filename, R_OK) == -1 ) {
		return -1;
	} else {
		listfile_entry* f = filelist;
		for(int i = 0;i<MAX_FILES;i++){
			if(strcmp(f[i].filename,filename)==0){
				return 1;
			}
		}
		for(int j = 0;j<MAX_FILES;j++){
			if(f[j].filename[0] == '\0'){
				int index=j;
				strcpy(f[j].filename,filename);
				int add = add_listwords(filename,filelist,htable_ptr,index);
				if(add==-2){
					return -2;
				} else  {	
					f[j].loaded=1;
					return 0;
				}	
			}
		}
		return 2;
	}
}

/**
   remove file from file table

   parameters :
   filename   : name of file to remove
   filelist   : pointer to table of files
   htable_ptr : pointer to hash table

   returns :
   -1 if file not in table
    0 if file removed
*/
int
remove_file(char filename[],
	    listfile_entry * filelist,
	    hash_table * htable_ptr)
{
	listfile_entry* f = filelist;
	for(int i=0;i<MAX_FILES;i++){
		if(strcmp(f[i].filename,filename)==0) {
			delete_in_hash(i,htable_ptr);
			f[i].filename[0]='\0';
			f[i].loaded=0;
			return 0;
		}
	}
  	return -1;
}

/*
  print file table (only loaded files)

  parameters :
   filelist : pointer to table of files
*/
void
print_list(listfile_entry * filelist)
{
	listfile_entry* f = filelist;
	printf("This is the file list \n");
	for(int i=0;i<MAX_FILES;i++){
		printf("%s  ", f[i].filename);
		printf("%d", f[i].loaded);
		printf("\n");	
	}
}

/**
   free file table

parameters :
   filelist   : pointer to table of files
*/
void
free_filelist(listfile_entry * filelist)
{
  free(filelist);
}

// ************************************************************************
// inner functions
// ************************************************************************

int
add_listwords(char filename[],
	 listfile_entry * filelist,
	 hash_table * htable_ptr,
	 int fileindex)
{
	FILE* fptr = fopen(filename,"r");
	char c = fgetc(fptr);
	char word[MAX_LENGTH]="";
	while(c != EOF) {
		while((c>='a' && c<='z') || (c>='A' && c<='Z')){
			strncat(word,&c,1);
			c = fgetc(fptr);
		}	
		if(strlen(word)>0) {
			int add = add_word(word,fileindex,htable_ptr);
			if(add != 0) {
				return -2;
			}
		}
		strcpy(word,"");
		c=fgetc(fptr);
	}
	return 0;
	fclose(fptr);

}
//Fct intermediaires qui rajoute les mots d'un fichier et !!! Malloc 

int
add_word(char word[], 
	int fileindex,
	hash_table * htable_ptr)
{
	int value = hashcode(word,MAX_ENTRIES);
	
	word_entry* tmp = htable_ptr->htable[value].first_word;
	
	word_entry * newElement = (word_entry*) malloc(sizeof(word_entry));                                                     
	strcpy(newElement->word,word);                                                                                          
	newElement->in_file=fileindex;                                                                                          
	newElement->times = 1;                                                                                                  
	newElement->next=NULL;
	
	if(newElement == NULL) {
		return -1;
	} else {
		if(tmp==NULL) {
			htable_ptr->htable[value].first_word=newElement;
			htable_ptr->htable[value].last_word=newElement;
			return 0;
		}
		//On check si le mot existe deja
		while((strcmp(word,tmp->word)!=0) && tmp->in_file!=fileindex) {
			tmp=tmp->next;
		}
		if(strcmp(word,tmp->word)==0){
			tmp->times++;
			free(newElement);
			return 0;	
		}else {		
			tmp->next=newElement;
			return 0;
		}
	}	
}


void
delete_in_hash(int fileindex,
		hash_table * htable_ptr){
	word_entry* current;
	for(int i=0;i<MAX_ENTRIES;i++) {
		word_entry* current = htable_ptr->htable[i].first_word;
		current = htable_ptr->htable[0].first_word;
		//if first word is from the fileindex to remove
		while(current != NULL && current->in_file == fileindex) {
			htable_ptr->htable[i].first_word = current->next;
			free(current);
			current = htable_ptr->htable[i].first_word;
		}
		//current is in middle or last
		if(current != NULL) {
			if(current->next != NULL) {
				while(current->next->in_file == fileindex && current->next != NULL){
					word_entry *tmp = current->next;
					current->next = tmp->next;
					free(tmp);
				}
			}
		}
	}
}
