package com.github.aimmoth.scala_stack.scala3.www

import scalatags.Text.all.*
import scalatags.stylesheet.StyleSheet

class StyleCss extends StyleSheet {

  def apply = {
    initStyleSheet()
    val styling = styleSheetText
    styling.replaceAll("com-github-aimmoth-scala_stack-scala3-www-StyleCss-",  "")
  }

  def body = cls(
    fontFamily := "arial",
    backgroundColor := "rgb(0, 119, 189)",
    color := "white"
  )

  def h1 = cls(
    letterSpacing := "2px"
  )

  def content = cls(
    backgroundColor := "white",
    color := "rgb(56, 68, 82)"
  )

  def more = cls(
    backgroundColor := "cyan"
  )

  def button = cls(
    backgroundColor := "rgb(56, 68, 82)",
    borderRadius := "4px",
    border := "none",
    color := "white"
  )
}
