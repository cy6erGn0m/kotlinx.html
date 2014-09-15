package kotlinx.html.test

import org.junit.*
import kotlin.test.*
import kotlinx.html.*

class HtmlBasicsTests() {
    Test fun testHtml() {
        val doc = html {}
        assertEquals("<!DOCTYPE html>\n<html/>", doc.toString())
    }

    Test fun testHtmlHead() {
        val doc = html { head {} }
        assertEquals("<!DOCTYPE html>\n<html>\n  <head></head>\n</html>", doc.toString())
    }

    Test fun testHtmlBody() {
        val doc = html { body {} }
        assertEquals("<!DOCTYPE html>\n<html>\n  <body></body>\n</html>", doc.toString())
    }

    Test fun testHtmlBodyAttribute() {
        val doc = html { body {
            id = "main"
            attribute("foo", "bar")
        } }
        assertEquals("<!DOCTYPE html>\n<html>\n  <body id=\"main\" foo=\"bar\"></body>\n</html>", doc.toString())
    }

    Test fun testSpanSpacing() {
        val doc = html { body {
            span { +"Text1" }
            span { +"Text2" }
        } }
        assertEquals("<!DOCTYPE html>\n<html>\n  <body><span>Text1</span><span>Text2</span></body>\n</html>", doc.toString())
    }
    Test fun testDivSpacing() {
        val doc = html { body {
            div { +"Text1" }
            div { +"Text2" }
        } }
        assertEquals("<!DOCTYPE html>\n<html>\n  <body>\n    <div>Text1</div>\n    <div>Text2</div>\n  </body>\n</html>", doc.toString())
    }
}
