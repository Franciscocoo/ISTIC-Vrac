/**************************************************************************
 * L3Informatique						C/Unix
 * 			TP linked lists
 *
 * Group 	:
 * Name 1 :
 * Name 2 :
 *
 **************************************************************************/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "../include/list.h"

/*
 * SYNOPSYS    :
 *   list_elem_t * create_element(int value)
 * DESCRIPTION :
 *   creates a new element, whose field next is initialized with NULL and the field
 *   value is initialized with the integer passed as argument.
 * PARAMETERS :
 *   value : value of the element
 * RESULT :
 *   NULL in case of error, otherwise a pointer over one strusture of type list_elem_t
 */
static list_elem_t *
create_element (int value)
{
  list_elem_t * newelt = malloc (sizeof (list_elem_t));
  if (newelt != NULL) {
    newelt->value = value;
    newelt->next = NULL;
  }
  return newelt;
}

/*
 * SYNOPSYS    :
 *   void free_element(list_elem_t * l)
 * DESCRIPTION :
 *   frees an element of the list.
 * PARAMETERS  :
 *   l : the pointer od the element to be freed
 * RESULT   :
 *   nothing
 */
static void
free_element (list_elem_t * l)
{
  free (l);
}

/*
 * SYNOPSYS :
 *   int insert_head(list_elem_t * * l, int value)
 * DESCRIPTION :
 *   Insert an element at the head of the list
 *   *l points the new head of the list.
 * PARAMETERS :
 *   list_elem_t ** l : pointer to the pointer of the head of the list
 *   int value : value of the element that is added
 * RESULT :
 *    0 in case of successful insertion
 *   -1 if the insertion is impossible
 */
int
insert_head (list_elem_t * * l, int value)
{
  if (l == NULL) { return -1; }
  list_elem_t * new_elt = create_element (value);
  if (new_elt == NULL) { return -1; }

  new_elt->next = *l;
  *l = new_elt;
  return 0;
}

/*
 * SYNOPSYS :
 *   int insert_tail(list_elem_t * * l, int value)
 * DESCRIPTION :
 *   Insert an element at the tail of the list (* l points the head of the list)
 * PARAMETERS :
 *   list_elem_t ** l : pointer to the pointer of the head of the list
 *   int value : value of the element that is added
 * RESULT :
 *    0 in case of successful insertion
 *   -1 if the insertion is impossible
 */
int
insert_tail(list_elem_t * * l, int value) {
  if ( l == NULL) { return -1; }
  list_elem_t * new_elt = create_element(value);
  if ( new_elt == NULL ) { return -1; }
  list_elem_t * p1 = *l;
  if ( *l == NULL ) { 
  	*l = new_elt;
  }
  else {
  	while (p1->next != NULL) {
		p1 = p1->next;
  	}
  	p1->next = new_elt;
  	new_elt->next = NULL;
  }
  return 0;
}

/*
 * SYNOPSYS :
 *   list_elem_t * find_element(list_elem_t * l, int index)
 * DESCRIPTION :
 *   Return a pointer of the element at the position n°i of the list
 *   (The first element is situated at the position 0).
 * PARAMETERS :
 *   int index : position of the element to be found
 *   list_elem_t * l : pointer of the head of the list
 * RESULTAT :
 *   - a pointer to the element of the list in cas of success
 *   - NULL in case of error
 */
list_elem_t *
find_element(list_elem_t * l, int index) {
  if (l == NULL) { return NULL; }
  if (index < 0) { return NULL; }
  list_elem_t * p1 = l;
  int i = 0;
  while ( i != index ) {
	p1 = p1->next;
        i++;
	if (p1 == NULL) { return NULL; }
  }
  return p1;
}

/*
 * SYNOPSYS :
 *   int remove_element(list_elem_t * * ppl, int value)
 * DESCRIPTION :
 *   Removes from the list the first element with a value equal to the argument value and 
 *   frees the memory space reserved by this element.
 *   Attention : Depending on the position, the head of the list may need to be modified
 * PARAMETERS :
 *   list_elem_t ** ppl : pointer to the pointer of the head of the list
 *   int value  : value to be removed from the list
 * RESULT :
 *    0 in case of success
 *   -1 in case of error
 */
int
remove_element(list_elem_t * * ppl, int value) {
  if ( ppl == NULL) { return -1; }
  list_elem_t * prec = *ppl;
  list_elem_t * courant = NULL;
  list_elem_t * suivant = NULL;
  while ( prec != NULL ) {
  	if(prec->value == value){
		*ppl=prec->next;		
		free_element(prec);
		return 0;
	}
	courant = prec->next;
        if(courant->value == value) {
		prec->next = courant -> next;		
		free_element(courant);
		return 0; 
	}
	suivant = courant->next;
	if(suivant->value == value){
		courant->next = suivant->next;
		free_element(suivant);
		return 0 ;
	}
	prec = prec->next;
  }
  return -1;
}

/*
 * SYNOPSYS :
 *   void reverse_list(list_elem_t * * l)
 * DESCRIPTION :
 *   Modifies the list by inversing the order of the elements.
 *   So the 1st element becomes the last element, the 2nd becomes the before last element, etc.)
 * PARAMETRES :
 *   list_elem_t ** l : pointer to the pointer of the head of the list
 * RESULTAT :
 *   aucun 
 */
void
reverse_list(list_elem_t * * l) {
  list_elem_t * prec = NULL;

  list_elem_t * courant = NULL;

  list_elem_t * suivant = NULL;

  courant = * l;

  while (courant != NULL ) {
        suivant = courant->next;
	courant->next = prec;
	prec=courant;
	courant=suivant;
  }
  * l = prec;
}
