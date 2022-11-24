# advent-of-code-2022

Solving puzzles from https://adventofcode.com/2022

One folder per programming language / environment in case I get bored of one.

## JVM

Setup to use Java 17 and Kotlin 1.7.

I've included [Manifold](http://manifold.systems/) to do some black magic. The things you can do with it are intensely
spooky.

### Gradle

Two custom tasks `setup-java-puzzle` and `setup-kotlin-puzzle` allow easy setup of the usual structure I use to
solve the puzzles. The folder `templates` contains the files I start with. Package structure is
`ch.neukom.advent2022.dayX` containing at least one file each for both parts of the puzzle.
