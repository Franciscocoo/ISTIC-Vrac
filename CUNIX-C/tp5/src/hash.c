#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "../include/functions.h" // global functions declarations

// ------------------------------------------------------------------------
// inner functions declarations
// ------------------------------------------------------------------------

// TO BE COMPLETED

//------------------------------------------------------------------------
// global functions definitions
//------------------------------------------------------------------------

/**
   create and initialize hash table

   returns : pointer to table or NULL if creation error
*/
hash_table *
create_table()
{
  hash_table *htable_ptr;
  htable_ptr = (hash_table*) malloc (sizeof(hash_table));
  htable_ptr->hsize = MAX_ENTRIES;
  htable_ptr->htable = (word_list*) malloc (sizeof(word_list) * htable_ptr->hsize);
  for(int i = 0;i<htable_ptr->hsize; i++) {
	htable_ptr->htable[i].first_word = NULL;
	htable_ptr->htable[i].last_word = NULL;
  }
  return htable_ptr;
}

/**
   search a word in table ; print word if found, with number of occurrences
   and file where word is found

   parameters :
   word : the word to look for
   filelist   : pointer to table of files
   htable_ptr : pointer to hash table

   returns : true if found, false otherwise
*/
int
search_word(char word[],
	    listfile_entry * filelist,
	    hash_table * htable_ptr)
{
  int val = hashcode(word,MAX_ENTRIES);
  //Parcours de la hashtable selon le int
  word_entry* tmp = htable_ptr->htable[val].first_word;
  bool res = false;
  while(tmp != NULL){
	if(strcmp(word,tmp->word)==0){
		printf("le mot est present dans le word_entry n°%d \n", val);
		printf("Il est present %d fois \n", tmp->times);
		printf("Il se situe dans le fichier d'indice %d \n", tmp->in_file);
		return 0;
	}
	tmp = tmp->next;
  }
  return -1;
}

/**
   print table contents

   parameters :
   htable_ptr : pointer to hash table
   filelist   : pointer to table of files
*/
void
print_table(hash_table * htable_ptr,
	    listfile_entry * filelist)
{
	hash_table * t = htable_ptr;
	printf("This is the hashtable \n");
	for(int i=0;i<htable_ptr->hsize;i++){
		word_entry* tmp = htable_ptr->htable[i].first_word;
		while(tmp->word != NULL){
			printf("-> The word_list n° %d contains :", i);
			printf("%s ", tmp->word);
			printf("\n");
			tmp=tmp->next;
		}
	}
}


/**
   free hash table

   parameters :
   htable_ptr : pointer to hash table
*/
void
free_table(hash_table * htable_ptr)
{
 	for(int i=0;i<htable_ptr->hsize;i++){
		word_entry* current = htable_ptr->htable[i].first_word;
        	while(current->word != NULL){
			word_entry* tmp = current->next;
			free(current);
			current = tmp;
		}
 	}
 	free(htable_ptr->htable);
 	free(htable_ptr);
}

// ------------------------------------------------------------------------
// inner functions definitions
// ------------------------------------------------------------------------

// TO BE COMPLETED
