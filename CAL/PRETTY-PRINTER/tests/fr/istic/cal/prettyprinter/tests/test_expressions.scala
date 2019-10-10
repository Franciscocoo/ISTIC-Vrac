package fr.istic.cal.prettyprinter.tests

import org.junit.Test
import org.junit.Assert._

import fr.istic.cal.prettyprinter.Prettyprinter._
import fr.istic.cal.prettyprinter._

class TestsExpressions {

  @Test
  def Test_prettyPrintExpr_Nl(): Unit = {
    assertEquals(
      "nil",
      prettyPrintExpr(Nl))
  }

  @Test
  def Test_prettyPrintExpr_Cst(): Unit = {
    assertEquals(
      "fact",
      prettyPrintExpr(Cst("fact")))
  }

  @Test
  def Test_prettyPrintExpr_VarExp(): Unit = {
    assertEquals(
      "X",
      prettyPrintExpr(VarExp("X")))
  }

  @Test
  def Test_prettyPrintExpr_Cons(): Unit = {
    assertEquals(
      "(cons X Y)",
      prettyPrintExpr(Cons(VarExp("X"), VarExp("Y"))))
  }

  @Test
  def Test_prettyPrintExpr_Hd(): Unit = {
    assertEquals(
      "(hd X)",
      prettyPrintExpr(Hd(VarExp("X"))))
  }

  @Test
  def Test_prettyPrintExpr_Tl(): Unit = {
    assertEquals(
      "(tl X)",
      prettyPrintExpr(Tl(VarExp("X"))))
  }

  @Test
  def Test_prettyPrintExpr_Eq(): Unit = {
    assertEquals(
      "X =? Y",
      prettyPrintExpr(Eq(VarExp("X"), VarExp("Y"))))
  }

  @Test
  def Test_prettyPrintExpr_Expr_1(): Unit = {
    assertEquals(
      "(hd X) =? (cons Y Z)",
      prettyPrintExpr(Eq(Hd(VarExp("X")), Cons(VarExp("Y"), VarExp("Z")))))
  }

  @Test
  def Test_prettyPrintExpr_Expr_2(): Unit = {
    assertEquals(
      "(hd (tl X)) =? (cons (cons Y Z) nil)",
      prettyPrintExpr(Eq(Hd(Tl((VarExp("X")))), Cons(Cons(VarExp("Y"), VarExp("Z")), Nl))))
  }
}

