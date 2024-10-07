# Scala Stack for Spring Boot 3

Scala Stack for Spring Boot 3, Scala 3. This project runs Spring Boot with 3 controllers:

1. One responds with HTML created with ScalaTags
2. One responds with CSS created with ScalaTags
3. The last one creates a JavaScript with the Scala Stack JS Compiler by reading local ScalaJS source files

This means all web content is created by request, no static files. If you are running a development service you can change most content and then refresh the page to see the changes.
Everything is also written in Scala which makes it easier to work with, can share logic and tests for instance.

## Project Structure

This is a Spring Boot 3 Maven project that uses Open API, to create API routes and models, and the Scala Stack JS Compiler (external library) to compile ScalaJS source to JavaScript. 

## Build

You need an external library to run this. It's public and available at Github.

1. Clone https://github.com/aimmoth/scala-stack-js-compiler and build locally. It's an SBT project and you can use `> sbt publishM2` to build local Maven library.
2. Generate sources with Maven `> mvn generate-sources`
3. Package with Maven `> mvn clean package`

You also need to copy the necessary libraries for the ScalaJS compilation. Copy the following libraries to the src/main/resources/libs folder:

- scala3-library_3-3.3.1.jar
- scala-library-2.13.10.jar
- scalajs-dom_sjs1_2.13-2.8.0.jar
- scalajs-javalib-1.12.0.jar
- scalajs-library_2.13-1.12.0.jar

## Run

Either use your IDE or Docker.

### Docker

You can run as a Docker image with the provided Dockerfile.

1. Build as instructed above
2. Build image with docker `> docker build -t scala-stack-spring-boot-3/latest .`
3. Run `> docker run -p 8081:8080 scala-stack-spring-boot-3/latest`
4. Optionally you can mount volumes to your local files. This means you can edit and compile while server is running. `> docker run -p 8081:8080 -v ".\src\main\resources\static\scalajs:APP/src" -v ".\src\main\resources\libs:APP/libs" scala-stack-spring-boot-3/latest`

## Windows

It's not possible to use the Scala Stack JS Compiler in Windows. But you can run the project in Docker and link your local files.
This means the docker images is running on one port 8081 and your local service run on 8080. When your page at 8080 needs the JavaScript, it will call the docker image, which compiles your local files.