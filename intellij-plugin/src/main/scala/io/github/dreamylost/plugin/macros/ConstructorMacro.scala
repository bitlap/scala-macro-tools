package io.github.dreamylost.plugin.macros

import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScClass

/**
 * constructor macro support
 *
 * @author 梦境迷离
 * @since 2021/7/5
 * @version 1.0
 */
object ConstructorMacro extends ScalaMacroTemplate {
  override def methodMacroTemplate(clazz: ScClass): String = ???
}
