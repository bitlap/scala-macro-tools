package io.github.dreamylost

import org.scalatest.{ FlatSpec, Matchers }

/**
 *
 * @author 梦境迷离
 * @since 2021/6/19
 * @version 1.0
 */
class BuilderTest extends FlatSpec with Matchers {

  "builder1" should "case class, non companion object" in {
    @builder
    case class TestClass1(val i: Int = 0, var j: Int, x: String, o: Option[String] = Some(""))
    // field : <caseaccessor> <paramaccessor> val i: Int = 0， so default value is "_"
    val ret = TestClass1.builder().i(1).j(0).x("x").build()
    println(ret)
    assert(ret.toString == "TestClass1(1,0,x,Some())")

  }

  "builder2" should "case class with companion object" in {
    @builder
    case class TestClass1(val i: Int = 0, var j: Int, x: String, o: Option[String] = Some(""))
    object TestClass1
    val ret = TestClass1.builder().i(1).j(0).x("x").build()
    println(ret)
    assert(ret.toString == "TestClass1(1,0,x,Some())")
  }

  "builder3" should "class with toString, non companion object" in {
    @toString //"toString" must be before "builder"
    @builder
    class TestClass1(val i: Int = 0, var j: Int, x: String, o: Option[String] = Some(""))
    val ret = TestClass1.builder().i(1).j(0).x("x").build()
    println(ret)
    assert(ret.toString == "TestClass1(i=1, j=0, x=x, o=Some())")
  }

  "builder4" should "class toString and companion object" in {
    @toString
    @builder
    class TestClass1(val i: Int = 0, var j: Int, x: String, o: Option[String] = Some(""))
    object TestClass1
    val ret = TestClass1.builder().i(1).j(0).x("x").build()
    println(ret)
    assert(ret.toString == "TestClass1(i=1, j=0, x=x, o=Some())")
  }

  "builder5" should "case class with toString and companion object" in {
    @toString
    @builder
    case class TestClass1(val i: Int = 0, var j: Int, x: String, o: Option[String] = Some(""))
    object TestClass1
    val ret = TestClass1.builder().i(1).j(0).x("x").build()
    println(ret)
    assert(ret.toString == "TestClass1(i=1, j=0, x=x, o=Some())")
  }

  "builder6" should "case class with toString, non companion object" in {
    @toString
    @builder
    case class TestClass1(val i: Int = 0, var j: Int, x: String, o: Option[String] = Some(""))
    val ret = TestClass1.builder().i(1).j(0).x("x").build()
    println(ret)
    assert(ret.toString == "TestClass1(i=1, j=0, x=x, o=Some())")
  }

}