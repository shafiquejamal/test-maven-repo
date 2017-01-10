# Create an artifact and publish to a privately hosted Maven repository

In this case, my privately hosted maven repository is at:

[mavenrepo.eigenroute.com](http://mavenrepo.eigenroute.com)

## Publishing

Adapt the following lines from build.sbt for your project:

```
version := "0.0.1"
organization := "com.eigenroute"

...

publishMavenStyle := true
val resolver = Resolver.ssh("Eigenroute maven repo", "mavenrepo.eigenroute.com", 22, "/home/mavenrepo/repo") withPermissions "0644"
publishTo := Some(resolver as ("mavenrepo", Path.userHome / ".ssh" / "id_rsa"))

```

From the TERMINAL (NOT SBT) command line, run:

```
sbt publish
sbt +publish
```

(It seems I need to use both ```sbt publish``` and ```sbt +publish``` in order to publish for both scala 2.10 and scala 2.11. Running the latter command from inside SBT causes an error.) 

## Adding to a project

To add this project as a dependency to your project (to test that the above works, this project has no other value), add the following lines to your SBT file:

```
resolvers += "Eigenroute maven repo" at "http://mavenrepo.eigenroute.com/"

libraryDependencies += "com.eigenroute" %% "test-maven-repo" % "0.0.1"
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