package fr.istic.prg1.tp3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.istic.prg1.tp3.InsertionInteger;

public class TestInsertionInteger2 {
	
	/**
	 * Implementation des fonctions données dans la classe testInsertionInteger
	 */
	
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	private static boolean equalIntegerArrays(int[] array1, int[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		if (length1 != length2) {
			return false;
		}
		int i = 0;
		while (i < length1 && array1[i] == array2[i]) {
			++i;
		}
		return (i == length1);
	}
	
	/**
	 * Insertion d'une valeur dans un tableau vide
	 */
	@Test
	public void testInsertionInteger1() {
		InsertionInteger insert = new InsertionInteger();
		boolean result = insert.insert(1);
		assertTrue(result);
	}
	
	/**
	 * Insertion de 2 fois la meme valeur dans un tableau vide
	 */
	@Test
	public void testInsertionInteger2() {
		InsertionInteger insert = new InsertionInteger();
		insert.insert(1);
		boolean result = insert.insert(1);
		assertFalse(result);
	}
	/**
	 * Insertion de 2 valeurs differentes dans un tableau vide
	 */
	@Test
	public void testInsertionInteger3() {
		InsertionInteger insert = new InsertionInteger();
		insert.insert(1);
		boolean result = insert.insert(2);
		assertTrue(result);
	}
	/**
	 * Insertion d'une valeur dans un tableau de taille 3 (inferieur a toutes les autres valeurs)
	 */
	@Test
	public void testInsertionInteger4() {
		InsertionInteger insert = new InsertionInteger();
		insert.insert(3);
		insert.insert(4);
		insert.insert(5);
		boolean result = insert.insert(2);
		assertTrue(result);
	}
	/**
	 * Insertion d'une valeur dans un tableau de taille 3 (superieur a toutes les autres valeurs)
	 */
	@Test
	public void testInsertionInteger5() {
		InsertionInteger insert = new InsertionInteger();
		insert.insert(3);
		insert.insert(4);
		insert.insert(5);
		boolean result = insert.insert(6);
		assertTrue(result);
	}
	/**
	 * Verification d'une insertion basique
	 */
	@Test
	public void testInsertionInteger6() {
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.insert(36);
		int[] tab = { 36 };
		assertTrue(ourArray.toString(),
				equalIntegerArrays(ourArray.toArray(), tab));
	}
	/**
	 * Verification de plusieurs insertion de grandes valeurs
	 */
	@Test
	public void testInsertionInteger7() {
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.insert(489);
		ourArray.insert(125);
		ourArray.insert(468);
		ourArray.insert(365);
		ourArray.insert(298);
		ourArray.insert(207);
		ourArray.insert(198);
		ourArray.insert(789);
		int[] tab = { 125,198,207,298,365,468,489,789 };
		assertTrue(ourArray.toString(),
				equalIntegerArrays(ourArray.toArray(), tab));
	}
	/**
	 * Verification d'insertion de 10 valeurs
	 */
	@Test
	public void testInsertionInteger8() {
		InsertionInteger myArray = new InsertionInteger();
		for(int i=0;i<10;i++) {
			myArray.insert(i);
		}
		int[] tab = { 0,1,2,3,4,5,6,7,8,9 };
		assertTrue(tab.toString(),
				equalIntegerArrays(myArray.toArray(), tab));
	}
	/**
	 * Verification d'insertion de 11 valeurs (donc la derniere n'est pas prise en compte)
	 */
	@Test
	public void testInsertionInteger9() {
		InsertionInteger myArray = new InsertionInteger();
		for(int i=0;i<11;i++) {
			myArray.insert(i);
		}
		int[] tab = { 0,1,2,3,4,5,6,7,8,9 };
		assertTrue(tab.toString(),
				equalIntegerArrays(myArray.toArray(), tab));
	}
}
