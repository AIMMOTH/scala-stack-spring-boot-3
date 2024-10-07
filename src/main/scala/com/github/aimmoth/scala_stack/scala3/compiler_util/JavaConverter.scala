package com.github.aimmoth.scala_stack.scala3.compiler_util

import com.github.aimmoth.scala_stack.js_compiler.{Classpath, Optimizer, ScalaStackJsCompiler}

import java.io.InputStream
import scala.jdk.CollectionConverters.*

class JavaConverter {

  /**
   * If you are using Spring Boot this is an example of a loader.
   * Scala:
   * <pre>
   *   val loader: (String => InputStream) = (jarFile: String) => {
   *     val uri = new URI(jarFile)
   *     val file = ResourceUtils.getFile(uri.getPath)
   *     new FileInputStream(file)
   *   }
   * </pre>
   * Java:
   * <pre>
   * scala.Function1<String, InputStream> loader = (filename) -> {
   *   try {
   *     var uri = new URI(filename);
   *     var file = ResourceUtils.getFile(uri.getPath());
   *     return new FileInputStream(file);
   *   } catch (Exception e) {
   *     log.log(Level.WARNING, e.getMessage(), e);
   *     return null;
   *   }
   * };
   * </pre>
   *
   * @param loader
   * @param relativePath
   * @param libs
   * @return
   */
  def createClasspath(scalaStackJsCompiler: ScalaStackJsCompiler, loader: (String => InputStream), relativePath: String, libs: java.util.Set[String]): Classpath = {
    scalaStackJsCompiler.init(loader, relativePath, libs.asScala.toSet)
  }

  def compileFast(scalaStackJsCompiler: ScalaStackJsCompiler, classpath: Classpath, scripts: java.util.List[String]) = {
    scalaStackJsCompiler.compileScalaJsStrings(classpath, scripts.asScala.toList, Optimizer.Fast)
  }
}