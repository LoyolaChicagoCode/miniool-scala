package edu.luc.cs.laufer.cs371.miniool

import org.scalatest.funsuite.AnyFunSuite
import scala.language.postfixOps

class TestImperative extends AnyFunSuite {

  val store = Map[String, Cell](
    "x" -> Cell(2),
    "y" -> Cell(3),
    "r" -> Cell(0)
  )

  val s =
    While(
      Variable("y"),
      Sequence(
        Assignment(Variable("r"), Plus(Variable("r"), Variable("x"))),
        Assignment(Variable("y"), Minus(Variable("y"), Constant(1)))
      )
    )

  test("Imperative") {
    assert(store - "x" - "y" - "r" isEmpty)
    assert(store("x").get.left.toOption.get === 2)
    assert(store("y").get.left.toOption.get === 3)
    assert(store("r").get.left.toOption.get === 0)
    Execute(store)(s)
    // Map(x -> Cell(Left(2)), y -> Cell(Left(0)), r -> Cell(Left(6)))
    assert(store - "x" - "y" - "r" isEmpty)
    assert(store("x").get.left.toOption.get === 2)
    assert(store("y").get.left.toOption.get === 0)
    assert(store("r").get.left.toOption.get === 6)
  }
}
