/*
 * Copyright (c) 2022 bitlap
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.bitlap.csv.core.macros

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import scala.reflect.macros.blackbox

/** This is a generic implementation of macro handling, and subclasses need to inherit it to reduce redundant code.
 *
 *  @author
 *    梦境迷离
 *  @since 2021/7/24
 *  @version 1.0
 */
abstract class AbstractMacroProcessor(val c: blackbox.Context) {

  import c.universe._

  protected val packageName = q"_root_.org.bitlap.csv.core"

  private[macros] def tryGetOrElse(tree: Tree, default: Tree): Tree =
    q"_root_.scala.util.Try($tree).getOrElse($default)"

  private[macros] def tryOptionGetOrElse(optionTree: Tree, default: Tree): Tree =
    q"_root_.scala.util.Try($optionTree.getOrElse($default)).getOrElse($default)"

  private[macros] def tryOption(optionTree: Tree): Tree =
    q"_root_.scala.util.Try($optionTree).getOrElse(None)"

  private[macros] def getDefaultValue(typ: Type): Tree =
    typ match {
      case t if t =:= typeOf[Int] =>
        q"0"
      case t if t =:= typeOf[String] =>
        val empty = ""
        q"$empty"
      case t if t =:= typeOf[Float] =>
        q"0.asInstanceOf[Float]"
      case t if t =:= typeOf[Double] =>
        q"0D"
      case t if t =:= typeOf[Char] =>
        q"'?'"
      case t if t =:= typeOf[Byte] =>
        q"0"
      case t if t =:= typeOf[Short] =>
        q"0"
      case t if t =:= typeOf[Boolean] =>
        q"false"
      case t if t =:= typeOf[Long] =>
        q"0L"
      case _ => q"null"
    }

  /** Get the list of case class constructor parameters and return the column index, column name, and parameter type
   *  that zip as a `List[((Int, Tree), Type)]`.
   *
   *  @param columnsFunc
   *    The function to get CSV row data temporary identifier, also known as a line.
   *  @tparam T
   *    Type of the case class.
   *  @return
   */
  private[macros] def checkCaseClassZipAll[T: c.WeakTypeTag](
    columnsFunc: TermName
  ): List[((Int, Tree), Type)] = {
    val idxColumn    = (i: Int) => q"$columnsFunc()($i)"
    val params       = getCaseClassParams[T]()
    val paramsSize   = params.size
    val types        = params.map(f => c.typecheck(tq"$f", c.TYPEmode).tpe)
    val indexColumns = (0 until paramsSize).toList.map(i => i -> idxColumn(i))
    if (indexColumns.size != types.size) {
      c.abort(c.enclosingPosition, "The column num of CSV file is different from that in case class constructor!")
    }

    indexColumns zip types
  }

  /** Get only the symbol of the case class constructor parameters.
   *
   *  @tparam T
   *    Type of the case class.
   *  @return
   */
  private[macros] def getCaseClassParams[T: c.WeakTypeTag](): List[Symbol] = {
    val parameters = resolveParameters[T]
    if (parameters.size > 1) {
      c.abort(c.enclosingPosition, "The constructor of case class has currying!")
    }
    parameters.flatten
  }

  /** Print the expanded code of macro.
   *
   *  @param force
   *  @param resTree
   *  @tparam T
   *  @return
   */
  def exprPrintTree[T: c.WeakTypeTag](force: Boolean, resTree: c.Tree): c.Expr[T] = {
    c.info(
      c.enclosingPosition,
      s"\n###### Time: ${ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME)} Expanded macro start ######\n" + resTree
        .toString() + "\n###### Expanded macro end ######\n",
      force = force
    )
    c.Expr[T](resTree)
  }

  /** Get the constructor symbol of the case class.
   *
   *  @tparam T
   *    Type of the case class.
   *  @return
   *    The parameters may be currying, so it's a two-level list.
   */
  private[macros] def resolveParameters[T: c.WeakTypeTag]: List[List[Symbol]] =
    c.weakTypeOf[T].resultType.member(TermName("<init>")).typeSignature.paramLists

  /** Get the `TypeName` of the class.
   *
   *  @tparam T
   *    Type of the case class.
   *  @return
   */
  private[macros] def resolveClazzTypeName[T: c.WeakTypeTag]: c.universe.TypeName =
    TypeName(c.weakTypeOf[T].typeSymbol.name.decodedName.toString)

  /** Get the list of case class constructor parameters and return the column index and parameter type that zip as a
   *  `List[(Int, Type)])`.
   *
   *  @tparam T
   *    Type of the case class.
   *  @return
   */
  private[macros] def checkCaseClassZip[T: c.WeakTypeTag]: (List[String], List[(Int, Type)]) = {
    val params     = getCaseClassParams[T]()
    val paramsSize = params.size
    val names      = params.map(p => p.name.decodedName.toString)
    names -> params.zip(0 until paramsSize).map(f => f._2 -> c.typecheck(tq"${f._1}", c.TYPEmode).tpe)
  }

  /** Get the builderId of the current class which generated by *Builder,apply macro.
   *
   *  @param annoBuilderPrefix
   *  @return
   */
  private[macros] def getBuilderId(annoBuilderPrefix: String): Int =
    c.prefix.actualType.toString.replace(annoBuilderPrefix, "").toInt
}
