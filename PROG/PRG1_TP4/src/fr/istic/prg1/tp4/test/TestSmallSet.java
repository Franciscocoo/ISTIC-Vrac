package fr.istic.prg1.tp4.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import fr.istic.prg1.tp4.SmallSet;

public class TestSmallSet {

	@Test
	public void TestSize1(){
		SmallSet s = new SmallSet();
		assertEquals(0,s.size());
	}
	
	@Test
	public void TestSize2(){
		boolean[] t = new boolean[256];
		t[1]=true;
		t[2]=true;
		t[3]=true;
		SmallSet s = new SmallSet(t);
		assertEquals(3,s.size());
	}
	
	@Test
	public void TestSize3(){
		boolean[] t = new boolean[256];
		for(int i=0;i<t.length;i++) {
			t[i]=true;
		}
		SmallSet s = new SmallSet(t);
		assertEquals(256,s.size());
	}
	
	@Test
	public void Testcontains1(){
		SmallSet s = new SmallSet();
		assertFalse(s.contains(4));
	}
	
	@Test
	public void Testcontains2(){
		boolean[] t = new boolean[256];
		t[4]=true;
		SmallSet s = new SmallSet(t);
		assertTrue(s.contains(4));
	}
	
	@Test
	public void TestIsEmpty1(){
		SmallSet s = new SmallSet();
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void TestIsEmpty2(){
		boolean[] t = new boolean[256];
		t[4]=true;
		SmallSet s = new SmallSet(t);
		assertFalse(s.isEmpty());
	}
	
	@Test
	public void Testadd1(){
		SmallSet s = new SmallSet();
		s.add(1);
		assertTrue(s.contains(1));
	}
	
	@Test
	public void Testadd2(){
		boolean[] t = new boolean[256];
		t[1]=true;
		t[2]=true;
		t[3]=true;
		SmallSet result = new SmallSet(t);
		SmallSet s = new SmallSet();
		s.add(1);
		s.add(2);
		s.add(3);
		assertEquals(s,result);
	}
	
	public void Testadd3(){
		boolean[] t = new boolean[256];
		t[1]=true;
		SmallSet result = new SmallSet(t);
		SmallSet s = new SmallSet();
		s.add(1);
		s.add(1);
		assertEquals(s,result);
	}
	
	@Test
	public void Testremove1(){
		boolean[] t = new boolean[256];
		t[1]=true;
		SmallSet s = new SmallSet(t);
		s.remove(1);
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void Testremove2(){
		SmallSet s = new SmallSet();
		s.add(3);
		s.add(4);
		s.remove(4);
		assertTrue(s.contains(3));
	}
	
	@Test
	public void Testremove3(){
		SmallSet s = new SmallSet();
		s.add(1);
		assertTrue(s.contains(1));
	}
	
	@Test
	public void Testaddinter1(){
		boolean[] t = new boolean[256];
		for(int i=0;i<10;i++) {
			t[i]=true;
		}
		SmallSet result = new SmallSet(t);
		SmallSet s = new SmallSet();
		s.addInterval(0, 9);
		assertEquals(s, result);
	}
	
	@Test
	public void Testaddinter2(){
		boolean[] t = new boolean[256];
		for(int i=0;i<256;i++) {
			t[i]=true;
		}
		SmallSet result = new SmallSet(t);
		SmallSet s = new SmallSet();
		s.addInterval(0, 255);
		assertEquals(s, result);
	}
	
	@Test
	public void TestremoveInterval(){
		boolean[] t = new boolean[256];
		t[21]=true;
		SmallSet result = new SmallSet(t);
		SmallSet s = new SmallSet();
		s.addInterval(10, 21);
		s.removeInterval(10, 20);
		assertTrue(s.contains(21));
		assertEquals(s,result);
	}
	
	@Test 
	public void TestUnion1() {
		boolean[] t = new boolean[256];
		for(int i=0;i<8;i++) {
			t[i]=true;
		}
		SmallSet result = new SmallSet(t);
		SmallSet s1 = new SmallSet();
		s1.add(0);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		SmallSet s2 = new SmallSet();
		s2.add(4);
		s2.add(5);
		s2.add(6);
		s2.add(7);
		s1.union(s2);
		assertEquals(s1,result);
	}
	
	@Test 
	public void TestUnion2() {
		boolean[] t = new boolean[256];
		for(int i=0;i<8;i++) {
			t[i]=true;
		}
		SmallSet result = new SmallSet(t);
		result.remove(4);
		SmallSet s1 = new SmallSet();
		s1.add(0);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		SmallSet s2 = new SmallSet();
		s2.add(3);
		s2.add(5);
		s2.add(6);
		s2.add(7);
		s1.union(s2);
		assertEquals(s1,result);
	}
	
	@Test 
	public void TestUnion3() {
		boolean[] t = new boolean[256];
		t[0]=true;
		t[255]=true;
		SmallSet result = new SmallSet(t);
		SmallSet s1 = new SmallSet();
		s1.add(0);
		SmallSet s2 = new SmallSet();
		s2.add(255);
		s1.union(s2);
		assertEquals(s1,result);
	}
	
	@Test 
	public void TestUnion4() {
		boolean[] t = new boolean[256];
		t[1]=true;
		t[2]=true;
		t[3]=true;
		SmallSet result = new SmallSet(t);
		SmallSet s1 = new SmallSet();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		SmallSet s2 = new SmallSet();
		s1.union(s2);
		assertEquals(s1,result);
	}
	
	@Test 
	public void TestUnion5() {
		SmallSet s1 = new SmallSet();
		SmallSet s2 = new SmallSet();
		s1.union(s2);
		assertTrue(s1.isEmpty());
	}
	
	@Test 
	public void TestInter1() {
		SmallSet s1 = new SmallSet();
		SmallSet s2 = new SmallSet();
		s1.intersection(s2);
		assertTrue(s1.isEmpty());
	}
	
	@Test 
	public void TestInter2() {
		SmallSet s1 = new SmallSet();
		s1.add(1);
		SmallSet s2 = new SmallSet();
		s1.intersection(s2);
		assertTrue(s1.isEmpty());
	}
	
	@Test 
	public void TestInter3() {
		SmallSet s1 = new SmallSet();
		s1.add(1);
		SmallSet s2 = new SmallSet();
		s2.add(1);
		s1.intersection(s2);
		assertTrue(s1.contains(1));
		assertEquals(s1.size(),1);
	}
	
	@Test 
	public void TestInter4() {
		SmallSet s1 = new SmallSet();
		s1.add(30);
		s1.add(33);
		s1.add(36);
		s1.add(39);
		SmallSet s2 = new SmallSet();
		s2.add(35);
		s2.add(36);
		s2.add(38);
		s2.add(39);
		s1.intersection(s2);
		assertTrue(s1.contains(36));
		assertTrue(s1.contains(39));
		assertEquals(s1.size(),2);
	}
	
	@Test 
	public void Testdiff1() {
		SmallSet s1 = new SmallSet();
		SmallSet s2 = new SmallSet();
		s1.difference(s2);
		assertTrue(s1.isEmpty());
	}
	
	@Test 
	public void Testdiff2() {
		SmallSet s1 = new SmallSet();
		SmallSet s2 = new SmallSet();
		s2.add(128);
		s1.difference(s2);
		assertTrue(s1.isEmpty());
	}
	
	@Test 
	public void Testdiff3() {
		SmallSet s1 = new SmallSet();
		s1.add(5);
		s1.add(128);
		SmallSet s2 = new SmallSet();
		s2.add(128);
		s1.difference(s2);
		assertTrue(s1.contains(5));
		assertEquals(s1.size(),1);
	}
	
	@Test 
	public void Testdiff4() {
		SmallSet s1 = new SmallSet();
		s1.add(5);
		s1.add(6);
		SmallSet s2 = new SmallSet();
		s2.add(128);
		s2.add(129);
		s2.add(123);
		s1.difference(s2);
		assertTrue(s1.contains(5));
		assertTrue(s1.contains(6));
		assertEquals(s1.size(),2);
	}
	
	@Test 
	public void Testdiffsym1() {
		SmallSet s1 = new SmallSet();
		SmallSet s2 = new SmallSet();
		s1.symmetricDifference(s2);
		assertTrue(s1.isEmpty());
	}
	
	@Test 
	public void Testdiffsym2() {
		SmallSet s1 = new SmallSet();
		SmallSet s2 = new SmallSet();
		s2.add(32);
		s1.symmetricDifference(s2);
		assertFalse(s1.isEmpty());
	}
	
	@Test 
	public void Testdiffsym3() {
		boolean[] t = new boolean[256];
		t[1]=true;
		t[2]=true;
		t[33]=true;
		t[34]=true;
		SmallSet result = new SmallSet(t);
		SmallSet s1 = new SmallSet();
		s1.add(1);
		s1.add(2);
		s1.add(32);
		SmallSet s2 = new SmallSet();
		s2.add(32);
		s2.add(33);
		s2.add(34);
		s1.symmetricDifference(s2);
		assertEquals(s1.size(),4);
		assertEquals(s1,result);
	}
	
	@Test
	public void Testcomplement1() {
		boolean[] t = new boolean[256];
		for(int i=1;i<256;i++) {
			t[i]=true;
		}
		SmallSet s1 = new SmallSet(t);
		s1.complement();
		assertTrue(s1.contains(0));
		assertEquals(s1.size(),1);
	}
	
	@Test
	public void Testcomplemen2() {
		boolean[] t = new boolean[256];
		for(int i=0;i<256;i++) {
			t[i]=true;
		}
		SmallSet result = new SmallSet(t);
		SmallSet s1= new SmallSet();
		s1.complement();
		assertEquals(s1,result);
	}
	
	@Test
	public void Testcomplemen3() {
		boolean[] t = new boolean[256];
		for(int i=0;i<256;i++) {
			t[i]=true;
		}
		SmallSet s1 = new SmallSet(t);
		s1.complement();
		SmallSet s2 = new SmallSet();
		assertEquals(s1,s2);
	}
	
	@Test
	public void Testclear() {
		SmallSet s1 = new SmallSet();
		s1.add(1);
		s1.clear();
		assertTrue(s1.isEmpty());
	}
	
	@Test
	public void Testclear2() {
		boolean[] t = new boolean[256];
		for(int i=0;i<256;i++) {
			t[i]=true;
		}
		SmallSet s1 = new SmallSet(t);
		s1.clear();
		assertTrue(s1.isEmpty());
	}
	
	@Test
	public void TestIsIncludeIn() {
		SmallSet s1 = new SmallSet();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		SmallSet s2 = new SmallSet();
		s2.add(3);
		s2.add(4);
		assertFalse(s1.isIncludedIn(s2));
	}
	
	@Test
	public void TestIsIncludeIn2() {
		SmallSet s2 = new SmallSet();
		s2.add(1);
		s2.add(2);
		s2.add(3);
		s2.add(4);
		SmallSet s1 = new SmallSet();
		s1.add(1);
		s1.add(5);
		assertFalse(s1.isIncludedIn(s2));
	}
}
