package edu.luc.cs.laufer.cs371.miniool

import SyntacticTypes.{Method, MethodBinding}

object SyntacticTypes:
  /**
    * A method has a list of local variables and a body.
    */
  type Method = (Seq[String], Statement)
  /**
    * A method binding is an entry in a map from method names to methods.
    */
  type MethodBinding = (String, Method)
end SyntacticTypes

/**
  * An abstract superclass for concrete Statement implementation classes.
  */
abstract class Statement

/**
  * A binary statement with two non-null children.
  */
abstract class BinaryStatement(left: Statement, right: Statement) extends Statement

/**
  * Syntax for applicative (side-effect-free) statements.
  */
case class Constant(value: Int) extends Statement
case class Plus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Minus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Times(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Div(left: Statement, right: Statement) extends BinaryStatement(left, right)

/**
  * Syntax for imperative statements, that is, those that are interesting because of their
  * side effects.
  */
case class Variable(name: String) extends Statement
case class Sequence(statements: Statement*) extends Statement
case class While(guard: Statement, body: Statement) extends BinaryStatement(guard, body)
case class Assignment(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class If(guard: Statement, thenBranch: Statement, elseBranch: Statement) extends Statement

/**
  * Syntax for statements for creating and using records.
  * New is implemented as a non-case class to allow the companion object
  * to hide the function behind a by-name argument and add back the
  * syntactic sugar to make it look like a case class.
  */
class New(val clazz: () => Clazz) extends Statement
object New:
  def apply(clazz: => Clazz) = new New(() => clazz)
  def unapply(s: Statement) = s match
    case n: New => Some(n.clazz())
    case _      => None
end New
case class Selection(receiver: Statement, field: String) extends Statement
case class Message(receiver: Statement, method: String, arguments: Statement*) extends Statement

/**
  * Syntax for classes. Not part of the Statement hierarchy
  * because they appear only as arguments to New Statements.
  * Note that methods are defined in terms of their local variables
  * and their body; arguments are numbered instead of named.
  */
case class Clazz(zuper: Option[Clazz], fields: Seq[String], methods: Seq[MethodBinding]):
  require(!fields.contains(null))
  require(!methods.contains(null))

  def this(zuper: Option[Clazz], fields: String*) = this(zuper, fields, Seq())
  def this(zuper: Clazz, fields: Seq[String], methods: Seq[MethodBinding]) = this(Some(zuper), fields, methods)
  def this(fields: Seq[String], methods: Seq[MethodBinding]) = this(None, fields, methods)
  def this(fields: String*) = this(None, fields, Seq())
  def this() = this(None, Seq(), Seq())
end Clazz
