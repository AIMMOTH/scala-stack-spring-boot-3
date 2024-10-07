import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}


@JSExportTopLevel("Logic")
class SimpleLogic {

  @JSExport
  def information(text: String) = {
    val paragraph = document.createElement("p")
    paragraph.textContent = "This is a paragraph added to the DOM with the text:" + text
    document.body.appendChild(paragraph)
  }

}
