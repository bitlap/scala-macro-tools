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

package org.bitlap.csv.core

/**
 *
 * @author 梦境迷离
 * @version 1.0,2022/5/1
 */
trait CsvableImplicits {

  lazy val LINE_SEPARATOR: String = "\n"

  implicit val stringCSVCsvable: Csvable[String] = new Csvable[String] {
    override def _toCsvString(s: String): String = s
  }

  implicit val intCsvCsvable: Csvable[Int] = new Csvable[Int] {
    override def _toCsvString(i: Int): String = i.toString
  }

  implicit val charCsvCsvable: Csvable[Char] = new Csvable[Char] {
    override def _toCsvString(t: Char): String = t.toString
  }

  implicit val longCsvCsvable: Csvable[Long] = new Csvable[Long] {
    override def _toCsvString(i: Long): String = i.toString
  }

  implicit val shortCsvCsvable: Csvable[Short] = new Csvable[Short] {
    override def _toCsvString(i: Short): String = i.toString
  }

  implicit val doubleCsvCsvable: Csvable[Double] = new Csvable[Double] {
    override def _toCsvString(i: Double): String = i.toString
  }

  implicit val floatCsvCsvable: Csvable[Float] = new Csvable[Float] {
    override def _toCsvString(i: Float): String = i.toString
  }

  implicit val booleanCsvCsvable: Csvable[Boolean] = new Csvable[Boolean] {
    override def _toCsvString(i: Boolean): String = i.toString
  }
}
