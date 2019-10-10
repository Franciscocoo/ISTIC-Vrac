package fr.istic.cal.cantor

object cantor extends App {
  
  // EXERCICE 1

  /**
   * @param p : une paire d'entiers naturels
   * @return le nombre de Cantor de la paire p calculé de façon directe
   */
  // TODO TP1
  def rang(p: (Int, Int)): Int = { 
    val x = p._1
    val y = p._2
    val n = (((x+y)*(x+y+1))/2)+x
    return n
    }
  

  /**
   * @param p : une paire (x,y) d'entiers naturels
   * @return le nombre de Cantor de la paire p calculé récursivement
   */
  // TODO TP1
  def rangRec(p: (Int, Int)): Int = {
    p match {
      case (0,0) => 0
      case (0,_) => rangRec(p._2-1,0)+1
      case (_,_) => rangRec(p._1-1,p._2+1)+1
    }
  }

  /**
   * @param n : un naturel
   * @return la paire (x,y) dont n est le nombre de Cantor
   */
  // TODO TP1
  def enum(n: Int): (Int, Int) = {
    var max : Int = 0
    while((max*(max+1))/2 <= n){
      max= max+1
    }
    max=max-1
    var x = n - ((max)*(max+1)/2)
    var y = max - x
    return (x,y)
  }
  
  
  // EXERCICE 2
  
  /**
   * @param t : un triplet (x,y,z) d'entiers naturels
   * @return un nombre de Cantor du triplet t
   */
  // TODO TP1
  def rangTriplet(t: (Int, Int, Int)): Int = { 
    rang(rang(t._1,t._2),t._3)
  }

  /**
   * @param n : un naturel
   * @return le triplet (x,y,z) dont n est un nombre de Cantor
   */
  // TODO TP1
  def enumTriplet(n: Int): (Int, Int, Int) = {
     var res = enum(n)
     var res2 = enum(res._1)
     return (res2._1,res2._2,res._2)
  }

   
  // EXERCICE 3
  
  /**
   * @param l : une liste d'entiers naturels
   * @return un nombre de Cantor de la liste l
   */
  // TODO TP1
  def rangListe(l: List[Int]): Int = {
    l match {
      case null => 0
      case h :: Nil => return rang(h,0)
      case h :: c :: fin => rangListe((rang(h,c) :: fin))
    }
  }

  /**
   * @param n : un entier correspondant à un nombre de Cantor calculé par RangListe
   * @return le nombre de Cantor de la liste l
   */
  // TODO TP1
 /* def enumListe(n: Int): List[Int] = { 
    n match {
      case 0 => Nil
      case _ => var x = enum(n)._1
                var y = enum(n)._2
                List(y :: enum(x))
    }
  }*/
   
  // EXERCICE 4
  
  /**
   * @param s une chaine de caractères
   * @return un nombre de cantor de la chaîne s
   */
  def rangChaine(s : String) : Int = { ???
  }
  
  /**
   * @param n un nombre de Cantor d'une chaîne de caractères
   * @return la chaîne de caractères dont le nombre de Cantor est n
   */
  def enumChaine(n : Int) : String = { ???
  }
  
}