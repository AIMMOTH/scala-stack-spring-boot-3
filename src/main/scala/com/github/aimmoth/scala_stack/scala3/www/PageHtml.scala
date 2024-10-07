package com.github.aimmoth.scala_stack.scala3.www

import scalatags.Text.all.*

class PageHtml {

  def apply(javascriptUrl: String) = "<!doctype html>" + htmlContent(javascriptUrl).toString

  private def htmlContent(javascriptUrl: String) = html(
    head(
      tag("title")("Scala Stack"),
      link(rel := "stylesheet", href := "/style.css"),
      meta(name := "viewport", content := "width-device-width", attr("initial-scale") := "1.0")
    ),
    body(cls := "body",
      h1(cls := "h1", "Scala Stack"),
      div(cls := "content",
        p("This is an example of how to use Scala Stack with web development."),
        ul(
          li("HTML, CSS and JavaScript are all written in Scala"),
          li("Everything is created on a request"),
          li("If you want to speed things up you can cache it"),
        ),
        div(
          button(cls := "button", "Click me!", onclick := "Logic().information('I love Scala Stack!')")
        ),

        p("By Carl Emmoth (github.com/aimmoth)"),
      ),
      script(src := javascriptUrl),
      p(hidden, "Built at " + System.currentTimeMillis())
    )
  )
}
