package com.mchange.codegenutil

object UnitIndent:
  def fromInt(i : Int) : UnitIndent = i
  given UnitIndent = 2
opaque type UnitIndent = Int

extension (i : UnitIndent)
  def toInt : Int = i