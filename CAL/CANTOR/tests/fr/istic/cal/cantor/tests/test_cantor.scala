package fr.istic.cal.cantor.tests 

import org.junit.Test

import org.junit.Assert._

import fr.istic.cal.cantor.cantor._
import fr.istic.cal.cantor._

class Tests_Cantor {
  
  @Test
  def test_rang_1() : Unit = {
    assertEquals(0,rang(0,0))
  }
  
  @Test
  def test_rang_2() : Unit = {
    assertEquals(18,rang(3,2))
  }
  
  @Test
  def test_rangRec_1() : Unit = {
    assertEquals(0,rangRec(0,0))
  }
  
  @Test
  def test_rangRec_2() : Unit = {
    assertEquals(10,rangRec(0,4))
  }
  
  @Test
  def test_rangRec_3() : Unit = {
    assertEquals(18,rangRec(3,2))
  }
  
  @Test
  def test_enum_1() : Unit = {
    assertEquals((0,0),enum(0))
  }
  
  @Test
  def test_enum_2() : Unit = {
    assertEquals((3,2),enum(18))
  }
  
  @Test
  def test_enum_rang_1() : Unit = {
    assertEquals((0,0),enum(rang(0,0)))
  }
  
  @Test
  def test_enum_rang_2() : Unit = {
    assertEquals((3,2),enum(rang(3,2)))
  }
  
  @Test
  def test_enumTriplet_rangTriplet() : Unit = {
    assertEquals((1,2,3),enumTriplet(rangTriplet(1,2,3)))
  }
  
  @Test
  def test_enumListe_rangListe() : Unit = {
    assertEquals(List(1,2,3),enumListe(rangListe(List(1,2,3))))
  }
  
  @Test
  def test_enumChaine_rangChaine_1() : Unit = {
    assertEquals("++",enumChaine(rangChaine("++")))
  }
   
  // @Test
  // def test_enumChaine_rangChaine_2() : Unit = {
  //   assertEquals("+++",enumChaine(rangChaine("+++")))
  // }
}