package fr.istic.cal.prettyprinter

import scala.util.parsing.combinator.RegexParsers


object WhileParser extends RegexParsers {
  
   def constanteetnil= """[a-z]+[0-9]*""".r ^^ {case "nil" => Nl
                                                case x => Cst(x.toString)} 
  def variableexp= """[A-Z]+[0-9]*""".r ^^ {case x => VarExp(x.toString)}
  
  def expressioncons: Parser[Expression] = "(" ~ "cons" ~ expression ~ expression ~ ")" ^^ { case "(" ~ cons ~ e1 ~ e2 ~ ")" => Cons(e1,e2)}
  def expressionhd: Parser[Expression] = "(" ~ "hd" ~ expression ~ ")" ^^ {case "(" ~ "hd" ~ e ~ ")" => Hd(e)}
  def expressiontl: Parser[Expression] = "(" ~ "tl" ~ expression ~ ")" ^^ {case "(" ~ "tl" ~ e ~ ")" => Tl(e)} 
  def expressionsansegal: Parser[Expression] = constanteetnil | variableexp | expressioncons | expressionhd | expressiontl
  
  def expression: Parser[Expression] = expressionsansegal ~ opt("=?" ~ expression) ^^ {
                case e1 ~ None => e1
                case e1 ~ Some(_ ~ e2) => Eq(e1,e2)} | "(" ~ expression ~ ")" ^^ {case _ ~ e ~ _ => e}  
                
  def nop: Parser[Command] = "nop" ^^ {case _ => Nop}
  def variable: Parser[Variable]= """[A-Z]+[0-9]*""".r ^^ {case x => Var(x.toString)}  
  def twhile: Parser[Command]= "while" ~ expression ~ "do" ~ commandlist ~"od" ^^ {case _ ~ e ~ _ ~ l ~ _ => While(e,l)} 
  def tfor: Parser[Command]= "for" ~ expression ~ "do" ~ commandlist ~"od" ^^ {case _ ~ e ~ _ ~ l ~ _ => For(e,l)}   
  def tif: Parser[Command]= "if" ~ expression ~ "then" ~ commandlist ~ opt("else"~commandlist) ~"fi" ^^ {
      case _ ~ e ~ _ ~ l1 ~ None ~ _ => If(e,l1,List(Nop)) 
      case _ ~ e ~ _ ~ l1 ~ Some(_ ~ l2) ~ _ => If(e,l1,l2)}   
  def assign: Parser[Command]= variable ~ ":=" ~expression ^^ {case v ~_ ~e => Set(v,e)}
  def command: Parser[Command] = nop | assign | twhile | tfor | tif
  def commandlist: Parser[List[Command]] = command ~ opt(";" ~commandlist) ^^ {
                case c ~ None => List(c)
                case c ~ Some(_ ~l) => c::l}
  def variablelist: Parser[List[Variable]] = variable ~opt("," ~ variablelist) ^^ {
                case v ~ None => List(v)
                case v ~ Some(_ ~ l) => v::l}
    
  def program: Parser[Program] = "read" ~ variablelist ~ "%" ~ commandlist ~ "%" ~ "write" ~ variablelist ^^ {case _ ~ in ~ _ ~ c ~ _ ~ _ ~ out => Progr(in,c,out)}

  def analyserexpression(s: String) : Expression = {
    WhileParser.parseAll(expression, s) match {
      case Success(result, _) => result
      case Failure(msg, _) => throw new Exception("FAILURE: " + msg)
      case Error(msg, _) => throw new Exception("ERROR: " + msg)
    }    
  }
  
  def analysercommand(s: String) : Command = {
    WhileParser.parseAll(command, s) match {
      case Success(result, _) => result
      case Failure(msg, _) => throw new Exception("FAILURE: " + msg)
      case Error(msg, _) => throw new Exception("ERROR: " + msg)
    }       
  }
  def analyserprogram(s: String): Program = {
    WhileParser.parseAll(program, s) match {
      case Success(result, _) => result
      case Failure(msg, _) => throw new Exception("FAILURE: " + msg)
      case Error(msg, _) => throw new Exception("ERROR: " + msg)
    }
  }   

  
}


object TestSimpleParser extends App {
  print(WhileParser.analyserprogram("read X,  G,           K % \n Y := nil ; while X do Y := ( cons (hd X     ) Y); X := ( tl X) od % write Y, Z, T "))    
}