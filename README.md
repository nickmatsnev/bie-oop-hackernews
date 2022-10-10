

[![pipeline status](https://gitlab.fit.cvut.cz/BI-OOP/B201/hackernewsclient/badges/master/pipeline.svg)](https://gitlab.fit.cvut.cz/BI-OOP/B201/hackernewsclient/commits/master)
[![coverage report](https://gitlab.fit.cvut.cz/BI-OOP/B201/hackernewsclient/badges/master/coverage.svg)](https://gitlab.fit.cvut.cz/BI-OOP/B201/hackernewsclient/commits/master)

This is a template repository for the [Hacker News Client](https://courses.fit.cvut.cz/BI-OOP/projects/hackernews-cli.html) project. Feel free to fork it and leverage it as a starting point for your solution.

Before starting work on the project, make sure you can compile and run the prepared example code and tests.

The full project assignment can be found on the official [courses page](https://courses.fit.cvut.cz/BI-OOP/projects/hackernews-cli.html).

## Project Structure

```
.idea/          - Idea specific files
project/        - SBT configuration files
src/
├── main/
│   └── scala/  - Scala source files
└── test/
    └── scala/  - Scala tests
target/
.gitlab-ci.yml  - CI pipeline configuration
.scalafmt.conf  - scalafmt formatting rules
build.sbt       - SBT build file
hnc             - executable script
README.md      
```
![UML Class Diagram v.0.1](/uml/hnapi.png "UML Class Diagram v.0.1")

## Build

The project can be build either using IntelliJ Idea directly or by using the following `sbt` command:

```
sbt assembly
```

This command compiles the entire code, runs the tests and produces a jar including all dependencies.

## Usage

Same as building, the project can be run through the IntelliJ Idea; by running the sbt command:

```
sbt "run top-stories --page=2"
```

or, after building with the `sbt assembly`, by running the prepared executable script:

```
./hnc top-stories --page=2
```

## Support

Please contact Peter Matta via MS Teams or directly by email ([mattapet@fit.cvut.cz](mailto:mattapet.fit.cvut.cz)) in case you run into any issues or have any questions.
