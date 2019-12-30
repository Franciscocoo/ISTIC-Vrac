package fr.istic.prg1.tp3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.istic.prg1.tp3.Pair;
import fr.istic.prg1.tp3.InsertionPair;

public class TestInsertionPair2 {

	/**
	 * Implementation de la fonction donnée dans la classe testInsertionPair
	 */
	private static boolean equalPairArrays(Pair[] array1, Pair[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		if (length1 != length2) {
			return false;
		}
		boolean res = true;
		for (int i = 0; i < length1 && res; ++i) {
			if (array1[i] == null && array2[i] != null) {
				res = false;
			}
			if (array2[i] == null && array1[i] != null) {
				res = false;
			}
			if (array1[i] != null && array2[i] != null
					&& !array1[i].equals(array2[i])) {
				res = false;
			}
		}
		return res;
	}

	/**
	 * Insertion d'une paire dans un tableau vide
	 */
	@Test
	public void testInsertionPair1() {
		InsertionPair insert = new InsertionPair();
		boolean result = insert.insert(new Pair(1,1));
		assertTrue(result);
	}
	
	/**
	 * Insertion de 2 fois la meme paire dans un tableau vide
	 */
	@Test
	public void testInsertionPair2() {
		InsertionPair insert = new InsertionPair();
		insert.insert(new Pair(1,1));
		boolean result = insert.insert(new Pair(1,1));
		assertFalse(result);
	}
	/**
	 * Insertion de 2 paires differentes dans un tableau vide
	 */
	@Test
	public void testInsertionPair3() {
		InsertionPair insert = new InsertionPair();
		insert.insert(new Pair(1,1));
		boolean result = insert.insert(new Pair(2,2));
		assertTrue(result);
	}
	/**
	 * Insertion d'une paire dans un tableau de taille 3 (inferieur a toutes les autres paires)
	 */
	@Test
	public void testInsertionPair4() {
		InsertionPair insert = new InsertionPair();
		insert.insert(new Pair(3,4));
		insert.insert(new Pair(4,5));
		insert.insert(new Pair(5,6));;
		boolean result = insert.insert(new Pair(3,2));
		assertTrue(result);
	}
	/**
	 * Insertion d'une paire dans un tableau de taille 3 (superieur a toutes les autres paires)
	 */
	@Test
	public void testInsertionPair5() {
		InsertionPair insert = new InsertionPair();
		insert.insert(new Pair(3,4));
		insert.insert(new Pair(4,5));
		insert.insert(new Pair(5,6));;
		boolean result = insert.insert(new Pair(6,7));
		assertTrue(result);
	}
	/**
	 * Verification d'une insertion basique
	 */
	@Test
	public void testInsertionPair6() {
		InsertionPair ourArray = new InsertionPair();
		ourArray.insert(new Pair(36,36));
		Pair[] tab = { new Pair(36,36) };
		assertTrue(ourArray.toString(),
				equalPairArrays(ourArray.toArray(), tab));
	}
	/**
	 * Verification de 4 insertion
	 */
	@Test
	public void testInsertionPair7() {
		InsertionPair insert = new InsertionPair();
		insert.insert(new Pair(3,4));
		insert.insert(new Pair(4,5));
		insert.insert(new Pair(5,6));
		insert.insert(new Pair(1,2));
		Pair[] tab = { new Pair(1,2),
					new Pair(3,4),
					new Pair(4,5),
					new Pair(5,6)};
		assertTrue(insert.toString(),
				equalPairArrays(insert.toArray(), tab));
	}
	/**
	 * Verification de plusieurs insertion de grandes valeurs
	 */
	@Test
	public void testInsertionPair8() {
		InsertionPair insert = new InsertionPair();
		insert.insert(new Pair(3,4));
		insert.insert(new Pair(4,5));
		insert.insert(new Pair(5,6));
		insert.insert(new Pair(8,9));
		Pair[] tab = {new Pair(3,4),
					new Pair(4,5),
					new Pair(5,6),
					new Pair(8,9),};
		assertTrue(insert.toString(),
				equalPairArrays(insert.toArray(), tab));
	}
	/**
	 * Verification d'insertion de 10 paires
	 */
	@Test
	public void testInsertionPair9() {
		InsertionPair myArray = new InsertionPair();
		for(int i=1;i<11;i++) {
			myArray.insert(new Pair(i-1,i));
		}
		Pair[] tab = { new Pair(0,1),
					new Pair(1,2),
					new Pair(2,3),
					new Pair(3,4),
					new Pair(4,5),
					new Pair(5,6),
					new Pair(6,7),
					new Pair(7,8),
					new Pair(8,9)
					,new Pair(9,10) };
		assertTrue(tab.toString(),
				equalPairArrays(myArray.toArray(), tab));
	}
	/**
	 * Verification d'insertion de 11 valeurs (donc la derniere n'est pas prise en compte)
	 */
	@Test
	public void testInsertionPair10() {
		InsertionPair myArray = new InsertionPair();
		for(int i=1;i<12;i++) {
			myArray.insert(new Pair(i-1,i));
		}
		Pair[] tab = { new Pair(0,1),
					new Pair(1,2),
					new Pair(2,3),
					new Pair(3,4),
					new Pair(4,5),
					new Pair(5,6),
					new Pair(6,7),
					new Pair(7,8),
					new Pair(8,9)
					,new Pair(9,10) };
		assertTrue(tab.toString(),
				equalPairArrays(myArray.toArray(), tab));
	}

}
