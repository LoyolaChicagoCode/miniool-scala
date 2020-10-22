package edu.luc.cs.laufer.cs371.miniool

import scala.language.postfixOps
import org.scalatest.funsuite.AnyFunSuite

class TestWithInheritance extends AnyFunSuite {

  /*
    abstract class C {
        public String hello() { return 1; } }
    class D extends C {
            @Override public String hello() { return 2; }
            @Override public String world() { return super.hello() + hello(); }
    }
    class E extends D {
            @Override public String hello() { return 4; } }
  */

  val C = new Clazz(
    Seq(),
    Seq(
      "hello" -> (Seq(), Constant(1))
    )
  )

  val D = new Clazz(
    C,
    Seq(),
    Seq(
      "hello" -> (Seq(), Constant(2)),
      "world" -> (Seq(), Plus(Message(Variable("super"), "hello"), Message(Variable("this"), "hello")))
    )
  )

  val E = new Clazz(
    D,
    Seq(),
    Seq(
      "hello" -> (Seq(), Constant(4))
    )
  )

  /*
   * var a, b, d, e, f, g;
   */
  val store = Map[String, Cell](
    "c" -> Cell(0),
    "d" -> Cell(0),
    "e" -> Cell(0),
    "u" -> Cell(0),
    "v" -> Cell(0),
    "w" -> Cell(0),
    "x" -> Cell(0),
    "y" -> Cell(0)
  )

  val c = Sequence(
    Assignment(Variable("c"), New(C)),
    Assignment(Variable("d"), New(D)),
    Assignment(Variable("e"), New(E)),
    Assignment(Variable("u"), Message(Variable("c"), "hello")),
    Assignment(Variable("v"), Message(Variable("d"), "hello")),
    Assignment(Variable("w"), Message(Variable("d"), "world")),
    Assignment(Variable("x"), Message(Variable("e"), "hello")),
    Assignment(Variable("y"), Message(Variable("e"), "world"))
  )

  test("Inheritance") {
    Execute(store)(c)
    assert(store("u").get.left.toOption.get === 1)
    assert(store("v").get.left.toOption.get === 2)
    assert(store("w").get.left.toOption.get === 3)
    assert(store("x").get.left.toOption.get === 4)
    assert(store("y").get.left.toOption.get === 5)
  }
}
