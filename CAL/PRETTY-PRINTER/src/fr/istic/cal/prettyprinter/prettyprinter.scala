package fr.istic.cal.prettyprinter

import scala.util.Try

/**
* définition d'une exception pour le cas des listes vides de commandes
*/
  case object ExceptionListeVide extends Exception
  
object Prettyprinter{
  
  /**
   * UTILISATION D'UN ANALYSEUR SYNTAXIQUE POUR LE LANGAGE WHILE
   *
   * les 3 fonctions suivantes permettent de construire un arbre de syntaxe abstraite 
   * respectivement pour une expression, une commande, un programme
   */
  
  /**
   * @param s : une chaine de caractère représentant la syntaxe concrète d'une expression du langage WHILE
   * @return un arbre de syntaxe abstraite pour cette expression
   */
  def readWhileExpression(s : String) : Expression = { WhileParser.analyserexpression(s)}
  
  /**
   * @param s : une chaine de caractère représentant la syntaxe concrète d'une commande du langage WHILE
   * @return un arbre de syntaxe abstraite pour cette commande
   */
  def readWhileCommand(s : String) : Command= {WhileParser.analysercommand(s)}  
  
  /**
   * @param s : une chaine de caractère représentant la syntaxe concrète d'un programme du langage WHILE
   * @return un arbre de syntaxe abstraite pour ce programme
   */
  def readWhileProgram(s : String) : Program = {WhileParser.analyserprogram(s)}

  
  
  /**
   * UN PRETTY-PRINTER POUR LE LANGAGE WHILE
   *
   */

  
  /**
   * définition d'un type pour les spécifications d'indentation
   */
  type IndentSpec = List[(String, Int)]
  

  /**
   *  TRAITEMENT DES EXPRESSIONS DU LANGAGE WHILE
   */

  /**
   * @param expression : un AST décrivant une expression du langage WHILE
   * @return une chaîne représentant la syntaxe concrète de l'expression
   */
  def prettyPrintExpr(expression: Expression): String = { 
    expression match {
      case Nl => "nil"
      case Cst(n) =>  n
      case VarExp(n) => n
      case Cons(a1, a2) =>"(cons "+prettyPrintExpr(a1) + " "+ prettyPrintExpr(a2) + ")"
      case Hd(a) => "(hd " + prettyPrintExpr(a) + ")"
      case Tl(a) => "(tl " + prettyPrintExpr(a) + ")"
      case Eq(a1,a2) => prettyPrintExpr(a1) + " =? " + prettyPrintExpr(a2)
    }
  }

  
  /**
   *  FONCTIONS AUXILIAIRES DE TRAITEMENT DE CHAINES POUR L'INDENTATION DES COMMANDES
   *  OU LA PRESENTATION DU PROGRAMME
   */

  /**
   * * définition d'une valeur d'indentation par défaut
   */
   val indentDefault : Int = 1
  
  /**
   * recherche d'une valeur d'indentation dans une liste de spécifications d'indentation
   *
   * @param context une chaîne de caractères décrivant un contexte d'indentation
   * @param is une liste de spécifications d'indentation, chaque spécification étant un couple (contexte,indentation)
   * @return l'indentation correspondant à contexte
   */
  def indentSearch(context: String, is: IndentSpec): Int = {
     is match {
       case Nil => indentDefault
       case h :: t => if(context.equals(h._1)){
                      return h._2
                     } else {
                       indentSearch(context,t)
                     }
     }
   }
  
  /**
   * création d'une indentation
   *
   * @param n un nombre d'espaces
   * @return une chaîne de n espaces
   */
  def makeIndent(n: Int): String = {  
    n match {
      case 0 => ""
      case 1 => " " 
      case _ => " " + makeIndent(n-1)
    }
  }

  
  /**
   * ajout d'une chaîne devant chaque élément d'une liste de chaînes
   *
   * @param pref une chaîne
   * @param strings une liste de chaînes
   * @return une liste de chaînes obtenue par la concaténation de pref devant chaque élément de strings
   */
  def appendStringBeforeAll(pref: String, strings: List[String]): List[String] = {
    strings match {
     case Nil => Nil 
     case h :: t => (pref+h) :: appendStringBeforeAll(pref, t)
   }
  }

  
  /**
   * ajout d'une chaîne après chaque élément d'une liste de chaînes
   *
   * @param suff une chaîne
   * @param strings une liste de chaînes
   * @return une liste de chaînes obtenue par la concaténation de pref après chaque élément de strings
   */
  def appendStringAfterAll(suff: String, strings: List[String]): List[String] = {
    strings match {
      case Nil => Nil
      case h ::t => (h+suff) :: appendStringAfterAll(suff,t)
    }
  }

  
  /**
   * ajout d'une chaîne après chaque élément d'une liste de chaînes sauf le dernier
   *
   * @param suff une chaîne
   * @param strings une liste non vide de chaînes
   * @return une liste de chaînes obtenue par la concaténation de pref après chaque élément de strings
   *         sauf le dernier
   */
  def appendStringAfterAllButLast(suff: String, strings: List[String]): List[String] = { 
    strings match {
      case Nil => Nil
      case h::Nil => List(h)
      case h::t => (h+suff) :: appendStringAfterAllButLast(suff, t)
    }
  }

  
  /**
   * ajout d'une chaîne après le dernier élément d'une liste de chaînes
   *
   * @param suff une chaîne
   * @param strings une liste non vide de chaînes
   * @return une liste de chaînes obtenue par la concaténation de pref après le dernier élément de strings
   */
  def appendStringAfterLast(suff: String, strings: List[String]): List[String] = { 
    strings match {
      case Nil => Nil 
      case h::Nil => List(h+suff)
      case h::t => h::appendStringAfterLast(suff, t)
    }
  }
  
  
  /**
   *
   *  TRAITEMENT DES COMMANDES DU LANGAGE WHILE
   */

  /**
   * @param command : un AST décrivant une commande du langage WHILE
   * @param is : une liste de spécifications d'indentation
   * @return une liste de chaînes représentant la syntaxe concrète de la commande
   */
  def prettyPrintCommand(command: Command, is: IndentSpec): List[String] = { 
    command match {
      case Nop => List("nop")
      case Set(Var(v),e) => v + " := " + prettyPrintExpr(e) :: Nil 
      case While(c,l) => List("while " + prettyPrintExpr(c) + " do") ++ 
        appendStringBeforeAll(indentCom("WHILE",is),prettyPrintCommands(l,is)) ++ List("od")
      case For(c,b) => List("for " + prettyPrintExpr(c) + " do") ++
      appendStringBeforeAll(indentCom("FOR",is),prettyPrintCommands(b,is)) ++ List("od")
      case If(c,t,e) => List("if " + prettyPrintExpr(c) + " then") ++
      appendStringBeforeAll(indentCom("IF",is), prettyPrintCommands(t,is)) ++ List("else") ++
      appendStringBeforeAll(indentCom("IF",is), prettyPrintCommands(e,is)) ++ List("fi")
    }
  }

  /**
   * Fonction qui renvoie une chaine de caractère, correspondant à l'indentation voulu dans is
   */
  def indentCom(c : String, is : IndentSpec) : String = {
    val a : Int = indentSearch(c,is)
    val p : String = makeIndent(a)
    p
  }
  
  /**
   * @param commands : une liste d'AST décrivant une liste de commandes du langage WHILE
   * @param is : une liste de spécifications d'indentation
   * @return une liste de chaînes représentant la syntaxe concrète de la listes de commandes
   */
  def prettyPrintCommands(commands: List[Command], is: IndentSpec): List[String] = {
     commands match {
      case Nil => Nil
      case c :: Nil => prettyPrintCommand(c,is) 
      case c :: rest => appendStringAfterLast(" ;", prettyPrintCommand(c,is)) ++ prettyPrintCommands(rest,is)
     }
  }
  
  
 /**
   *
   *  TRAITEMENT DES PROGRAMMES DU LANGAGE WHILE
   */

  /**
   * @param vars : une liste décrivant les paramètres d'entrée d'un programme du langage WHILE
   * @return une liste de chaînes représentant la syntaxe concrète des paramètres d'entrée du programme
   */
 def prettyPrintIn(vars : List[Variable]): String = { 
   vars match {
     case Nil => ""
     case Var(h) :: Nil => h 
     case Var(h) :: t => h + ", " + prettyPrintIn(t)
   }
 }
 
 
  /**
   * @param vars : une liste décrivant les paramètres de sortie d'un programme du langage WHILE
   * @return une liste de chaînes représentant la syntaxe concrète des paramètres de sortie du programme
   */
 def prettyPrintOut(vars : List[Variable]): String = { 
   vars match {
     case Nil => ""
     case Var(h) :: Nil => h
     case Var(h) :: t => h + ", " + prettyPrintOut(t)
   }
 }
 
 
  /**
   * @param program : un AST décrivant un programme du langage WHILE
   * @param is : une liste de spécifications d'indentation
   * @return une liste de chaînes représentant la syntaxe concrète du programme
   */
  def prettyPrintProgram(program : Program, is: IndentSpec): List[String] = {
    program match {
      case Progr(in,body,out) => List("read " ++ prettyPrintIn(in)) ++ List("%") ++
      appendStringBeforeAll(indentCom("PROGR",is),prettyPrintCommands(body,is)) ++ List("%") ++ List("write " + prettyPrintOut(out))
    }
  }   
        
  /**
   * Fonction qui convertir une Liste de String en un seul String
   */
  def listToString(l : List[String]): String = {
    l match {
      case Nil => ""
      case h::t => h + listToString(t)
    }
  }
 /**
   * @param program : un AST décrivant un programme du langage WHILE
   * @param is : une liste de spécifications d'indentation
   * @return une chaîne représentant la syntaxe concrète du programme
   */
  def prettyPrint(program : Program, is: IndentSpec): String = {
    var l = prettyPrintProgram(program,is)
    listToString(appendStringAfterAllButLast("\n",l))
  }
 
 val program: Program =
    Progr(
      List(Var("X")),
      List(
        Set(Var("Y"), Nl),
        While(
          VarExp("X"),
          List(
            Set(Var("Y"), Cons(Hd(VarExp("X")), VarExp("Y"))),
            Set(Var("X"), Tl(VarExp("X")))))),
      List(Var("Y")));
  val is: IndentSpec = List(("PROGR", 2), ("WHILE", 5));

 def main(args: Array[String]): Unit = {
   val program: Program =
      Progr(
        List(Var("X")),
        List(
          Set(Var("Y"), Nl),
          While(
            VarExp("X"),
            List(
              Set(Var("Y"), Cons(Hd(VarExp("X")), VarExp("Y"))),
              Set(Var("X"), Tl(VarExp("X")))))),
        List(Var("Y")));
   
   val is: IndentSpec = List(("PROGR", 2), ("WHILE", 5));
      
   println(prettyPrint(program, is))
 }
}