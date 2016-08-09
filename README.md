# Create an artifact and publish to a privately hosted Maven repository

In this case, my privately hosted maven repository is at:

[mavenrepo.eigenroute.com](http://mavenrepo.eigenroute.com)

## Publishing

From the TERMINAL (NOT SBT) command line, run:

```
sbt publish
sbt +publish
```

(It seems I need to use both ```sbt publish``` and ```sbt +publish``` in order to publish for both scala 2.10 and scala 2.11. Running the latter command from inside SBT causes an error. 

## Adding to a project

To add this project as a dependency to your project (to test that the above works, this project has no other value), add the following lines to your SBT file:

```
resolvers += "Eigenroute maven repo" at "http://mavenrepo.eigenroute.com/"

libraryDependencies += "test-maven-repo" %% "test-maven-repo" % "0.1-SNAPSHOT"
```

You should then be able to import code from this project into your project, e.g.
 
```
# in a new SBT project, in the main file com.example.Hello

package com.example

import com.eigenroute.testmavenrepo.SomeTestConfigParams

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world! Here is proof that I can import code from the dependency I added: " +
      s"${SomeTestConfigParams.configParam1}, and ${SomeTestConfigParams.configParam2}")
  }
}
```