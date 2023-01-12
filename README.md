# mchange-codegenutil

This is a very trivial library, but in my [experience](https://github.com/swaldman/mchange-commons-java/tree/master/src/main/java/com/mchange/v2/codegen), this stuff
tends to come in handy.

```scala
val LineSep = ...

def nonEmptyStringOption(s : String)         : Option[String] = ...
def nonEmptyStringOption(o : Option[String]) : Option[String] = ...

def increaseIndent( spaces : Int )( block : String ) = ...
def decreaseIndent( spaces : Int )( block : String ) = ...
def prependEachLine( prefix : String )( block : String ) : String = ...

def increaseIndentLevel(block : String)(using ui : UnitIndent) = ...
def decreaseIndentLevel(block : String)(using ui : UnitIndent) = ...

def increaseIndentLevels(levels : Int)(block : String)(using ui : UnitIndent) = ...
def decreaseIndentLevels(levels : Int)(block : String)(using ui : UnitIndent) = ...

extension (w : java.io.Writer)
  def indent(indentLevel: Int)(s: String)(using ui : UnitIndent) : Unit = ...
  def writeln(s: String) : Unit = ...
  def writeln() : Unit = ...
  def indentln(indentLevel: Int)(s: String)(using ui : UnitIndent) : Unit = ...
```
and

```scala
object UnitIndent:
  def fromInt(i : Int) : UnitIndent = i
  given UnitIndent = 2
opaque type UnitIndent = Int

extension (i : UnitIndent)
  def toInt : Int = i
```

What's here now is an extremely minimalist set of Scala 3 utilities.
Most prominent are extension methods of `java.io.Writer` so that
`writeln(...)` methods and an `indentln(...)(...)` method are available.
Indents are rendered in spaced but described in levels. The number of
spaces in an indent is defined by a `given` (and so overridable) `UnitIndent`,
which is just an opaque wrapper around `Int`.

There are also methods to increase / decrease the indent of a block
of text embedded in a String, and to prefix each line of a block
(to, for example, create blockquotes in markdown with '> '.) All
trivial, all useful, for now one short [file of utilities](https://github.com/swaldman/mchange-codegenutil/blob/main/src/main/scala/com/mchange/codegenutil/core.scala)
and the [`UnitIndent` type](https://github.com/swaldman/mchange-codegenutil/blob/main/src/main/scala/com/mchange/codegenutil/UnitIndent.scala).