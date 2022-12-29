package com.mchange.codegenutil

import java.io.Writer
import scala.util.matching.Regex.Match

private val IndentIncreasePointRegex ="""(?:^|([\r\n]+))""".r
private val IndentDecreaseRegex ="""(?:^( *)|([\r\n]+ *))""".r

private def nullToBlank(s : String) = if s == null then "" else s

private def notNullOrElse[T]( target : T, replacement : T) =
  if target == null then target else replacement

val LineSep = Option(System.getProperty("line.separator")).getOrElse("\n")

def increaseIndent( spaces : Int )( block : String ) =
  if (spaces > 0)
    val indent = " " * spaces
    IndentIncreasePointRegex.replaceAllIn(block, m => nullToBlank(m.group(1)) + indent)
  else
    block

def decreaseIndent( spaces : Int )( block : String ) =
  def replace( m : Match ) =
    val matched = notNullOrElse( m.group(1), notNullOrElse( m.group(2), "" ) )
    val endspaces = matched.dropWhile(c => c == '\r' || c == '\n').length
    val truncate = math.min(endspaces,spaces)
    matched.substring(0, matched.length-truncate)

  if (spaces > 0)
    IndentDecreaseRegex.replaceAllIn(block, m => replace(m))
  else
    block

def increaseIndentLevel(block : String)(using ui : UnitIndent) = increaseIndent(ui.toInt)(block)
def decreaseIndentLevel(block : String)(using ui : UnitIndent) = decreaseIndent(ui.toInt)(block)

def increaseIndentLevels(levels : Int)(block : String)(using ui : UnitIndent) = increaseIndent(ui.toInt * levels)(block)
def decreaseIndentLevels(levels : Int)(block : String)(using ui : UnitIndent) = decreaseIndent(ui.toInt * levels)(block)

private val ii = increaseIndent
private val di = decreaseIndent

extension (w : Writer)
  def writeln(s: String) : Unit =
    w.write(s)
    w.write(LineSep)
  def writeln() : Unit = w.write(LineSep)
  def writeln(indentLevel: Int)(s: String)(using ui : UnitIndent) : Unit = writeln(ii(indentLevel * ui.toInt)(s))



