package io.github.dreamylost

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 *
 * @author 梦境迷离
 * @since 2021/6/28
 * @version 1.0
 */
class LogTest extends AnyFlatSpec with Matchers {

  "log1" should "ok on class" in {
    """@log(verbose=true) class TestClass1(val i: Int = 0, var j: Int) {
                log.info("hello")
             }""" should compile

    """@log class TestClass2(val i: Int = 0, var j: Int)""" should compile
    """@log() class TestClass3(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true) class TestClass4(val i: Int = 0, var j: Int)""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.JLog) class TestClass5(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.JLog) class TestClass6(val i: Int = 0, var j: Int)""" should compile
  }

  "log2" should "ok on case class" in {
    """@log(verbose=true) case class TestClass1(val i: Int = 0, var j: Int) {
                log.info("hello")
             }""" should compile

    """@log case class TestClass2(val i: Int = 0, var j: Int)""" should compile
    """@log() case class TestClass3(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true) case class TestClass4(val i: Int = 0, var j: Int)""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.JLog) case class TestClass5(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.JLog) case class TestClass6(val i: Int = 0, var j: Int)""" should compile
  }

  "log3" should "ok on object" in {
    """@log(verbose=true) object TestClass1 {
              log.info("hello")
           }""" should compile

    """@log object TestClass2""" should compile
    """@log() object TestClass3""" should compile
    """@log(verbose=true) object TestClass4""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.JLog) object TestClass5""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.JLog) object TestClass6""" should compile
  }

  "log4 log4j2" should "ok on object" in {
    """@log(verbose=true) object TestClass1 {
              log.info("hello")
           }""" should compile

    """@log object TestClass2""" should compile
    """@log() object TestClass3""" should compile
    """@log(verbose=true) object TestClass4""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.Log4j2) object TestClass5""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Log4j2) object TestClass6""" should compile
  }

  "log5 slf4j" should "ok on object" in {
    """@log(verbose=true) object TestClass1 {
              log.info("hello")
           }""" should compile

    """@log object TestClass2""" should compile
    """@log() object TestClass3""" should compile
    """@log(verbose=true) object TestClass4""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.Slf4j) object TestClass5""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Slf4j) object TestClass6""" should compile
  }

  "log6 log4j2" should "ok on class" in {
    """@log(verbose=true) class TestClass1(val i: Int = 0, var j: Int) {
                log.info("hello")
             }""" should compile

    """@log class TestClass2(val i: Int = 0, var j: Int)""" should compile
    """@log() class TestClass3(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true) class TestClass4(val i: Int = 0, var j: Int)""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.Log4j2) class TestClass5(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Log4j2) class TestClass6(val i: Int = 0, var j: Int)""" should compile
  }

  "log7 slf4j" should "ok on class" in {
    """@log(verbose=true) class TestClass1(val i: Int = 0, var j: Int) {
                log.info("hello")
             }""" should compile

    """@toString @builder @log class TestClass2(val i: Int = 0, var j: Int)""" should compile //Use with multiple annotations
    """@log() class TestClass3(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true) class TestClass4(val i: Int = 0, var j: Int)""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.Slf4j) class TestClass5(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Slf4j) class TestClass6(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Slf4j) class TestClass6(val i: Int = 0, var j: Int){ log.info("hello world") }""" should compile
    """@log(io.github.dreamylost.logs.LogType.Slf4j) class TestClass6(val i: Int = 0, var j: Int){ log.info("hello world") }""" should compile //default verbose is false
  }

  "log8 slf4j" should "ok on class and has object" in {
    """@log(verbose=true) class TestClass1(val i: Int = 0, var j: Int) {
                log.info("hello")
             }""" should compile

    """@toString @builder @log class TestClass2(val i: Int = 0, var j: Int)""" should compile //Use with multiple annotations
    """@log() class TestClass3(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true) class TestClass4(val i: Int = 0, var j: Int)""" should compile
    """@log(logType=io.github.dreamylost.logs.LogType.Slf4j) class TestClass5(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Slf4j) class TestClass6(val i: Int = 0, var j: Int)""" should compile
    """@log(verbose=true, logType=io.github.dreamylost.logs.LogType.Slf4j) class TestClass6(val i: Int = 0, var j: Int){ log.info("hello world") }""" should compile
    """@log(io.github.dreamylost.logs.LogType.Slf4j) @builder class TestClass6(val i: Int = 0, var j: Int){ log.info("hello world") }
      | @log(io.github.dreamylost.logs.LogType.Slf4j) object TestClass6 { log.info("hello world");builder() }""".stripMargin should compile //default verbose is false
  }

  "log9 slf4j" should "ok on class and it object" in {
    """
      |@log(io.github.dreamylost.logs.LogType.Slf4j) @builder  class TestClass6(val i: Int = 0, var j: Int){ log.info("hello world") }
      |@log(io.github.dreamylost.logs.LogType.Slf4j) object TestClass6 { log.info("hello world"); builder()}
      |""".stripMargin should compile
  }

  "log10 slf4j" should "failed on case class and it object" in {
    """
      |    @log(io.github.dreamylost.logs.LogType.Slf4j)
      |    @builder case class TestClass6(val i: Int = 0, var j: Int) {
      |      log.info("hello world")
      |    }
      |    @log(logType = io.github.dreamylost.logs.LogType.Slf4j) object TestClass6 {
      |      log.info("hello world"); builder()
      |    }
      |""".stripMargin shouldNot compile //The context of class was not passed in object macro
  }

  "log11 slf4j" should "ok on class and it object" in {
    """
      | @log(io.github.dreamylost.logs.LogType.Slf4j)
      | @builder  class TestClass6(val i: Int = 0, var j: Int) {
      |      log.info("hello world")
      |    }
      |    @log(logType = io.github.dreamylost.logs.LogType.Slf4j) object TestClass6 {
      |      log.info("hello world"); builder()
      |    }
      |""".stripMargin should compile

    """
      | @builder
      | @log(io.github.dreamylost.logs.LogType.Slf4j)
      |    class TestClass6(val i: Int = 0, var j: Int) {
      |      log.info("hello world")
      |    }
      |    @log(logType = io.github.dreamylost.logs.LogType.Slf4j) object TestClass6 {
      |      log.info("hello world"); builder()
      |    }
      |""".stripMargin should compile
  }

}
