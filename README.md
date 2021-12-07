<img align="right" width="30%" height="30%" src="img.png" alt="https://org.bitlap"/>

# scala-macro-tools

[![Build](https://github.com/bitlap/scala-macro-tools/actions/workflows/ScalaCI.yml/badge.svg)](https://github.com/bitlap/scala-macro-tools/actions/workflows/ScalaCI.yml)
[![codecov](https://codecov.io/gh/bitlap/scala-macro-tools/branch/master/graph/badge.svg?token=IA596YRTOT)](https://codecov.io/gh/bitlap/scala-macro-tools)
[![Maven Central deprecated](https://img.shields.io/maven-central/v/io.github.jxnu-liguobin/scala-macro-tools_2.13.svg?label=Maven%20Central%20deprecated)](https://search.maven.org/search?q=g:%22io.github.jxnu-liguobin%22%20AND%20a:%22scala-macro-tools_2.13%22)
[![Maven Central](https://img.shields.io/maven-central/v/org.bitlap/scala-macro-tools_2.13.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.bitlap%22%20AND%20a:%22scala-macro-tools_2.13%22)
[![Version](https://img.shields.io/jetbrains/plugin/v/17202-scala-macro-tools)](https://plugins.jetbrains.com/plugin/17202-scala-macro-tools)

Motivation
--

Learn Scala macro and abstract syntax tree.

> The project is currently experimental

[中文说明](./README_CN.md) | [English](./README.md)

# Environment

- Compile passed in Java 8、11
- Compile passed in Scala 2.11.12、2.12.14、2.13.6

# Features

- `@toString`
- `@json`
- `@builder`
- `@synchronized`
- `@log`
- `@apply`
- `@constructor`
- `@equalsAndHashCode`
- `@jacksonEnum`
- `@elapsed`
- `@JavaCompatible`
- `ProcessorCreator`

> The intellij plugin named `Scala-Macro-Tools` in marketplace.

# Document

[https://bitlap.org/lab/smt](https://bitlap.org/lab/smt)

# How to use

Add library dependency

```scala
"org.bitlap.tools" %% "scala-macro-tools" % "<VERSION>"
```

The artefacts have been uploaded to Maven Central. Importing the library into your build system (e.g gradle, sbt), is not enough. You need to follow an extra step.

| Scala 2.11                   | Scala 2.12                   | Scala 2.13                                          |
| ---------------------------- | ---------------------------- | --------------------------------------------------- |
| Import macro paradise plugin | Import macro paradise plugin | Enable compiler flag `-Ymacro-annotations` required |

```scala
addCompilerPlugin("org.scalamacros" % "paradise_<your-scala-version>" % "<plugin-version>")
```

Where `<your-scala-version>` must be the full scala version. For example 2.12.13, and not 2.12.

If that doesn't work, google for alternatives.

In version scala`2.13.x`, the functionality of macro paradise has been included in the scala compiler directly. However,
you must still enable the compiler flag `-Ymacro-annotations`.
