package kotlinx.html

import java.util.*

public abstract class HtmlElement(val containingElement: HtmlElement?, val contentStyle: ContentStyle = ContentStyle.block) {
    {
        appendTo(containingElement)
    }

    private fun appendTo(element: HtmlElement?) = element?.children?.add(this)

    public val children: MutableList<HtmlElement> = ArrayList<HtmlElement>()

    protected abstract fun renderElement(builder: StringBuilder, indent: String)

    public override fun toString(): String {
        val builder = StringBuilder()
        renderElement(builder, "")
        return builder.toString()
    }
}

public enum class RenderStyle {
    adaptive
    expanded
    empty
}

public enum class ContentStyle {
    block
    text
    propagate
}

private fun HtmlElement.computeContentStyle(): ContentStyle {
    return when (contentStyle) {
        ContentStyle.block, ContentStyle.text -> contentStyle
        ContentStyle.propagate -> if (children.all { it.computeContentStyle() == ContentStyle.text }) ContentStyle.text else ContentStyle.block
    }
}

private fun String.htmlEscapeTo(builder: StringBuilder) {
    val len = length()

    for (i in 0..len - 1) {
        val c = charAt(i)
        when (c) {
            '<' -> builder.append("&lt;")
            '>' -> builder.append("&gt;")
            '\"' -> builder.append("&quot;")
            '&' -> builder.append("&amp;")
            else -> builder.append(c)
        }
    }
}

public fun build<T : HtmlTag>(tag: T, contents: T.() -> Unit): T {
    tag.contents()
    return tag
}

public abstract class HtmlTag(containingTag: HtmlTag?, val tagName: String, val renderStyle: RenderStyle = RenderStyle.expanded, contentStyle: ContentStyle = ContentStyle.block) : HtmlElement(containingTag, contentStyle) {
    private val attributes = LinkedHashMap<String, String>()

    protected override fun renderElement(builder: StringBuilder, indent: String) {
        val count = children.size()
        builder.append(indent).append('<').append(tagName)
        renderAttributes(builder)

        when {
            count == 0 && renderStyle != RenderStyle.expanded -> {
                builder.append('/')
            }
            count != 0 && renderStyle == RenderStyle.empty -> {
                throw InvalidHtmlException("Empty tag has children")
            }
            children.all { it.computeContentStyle() == ContentStyle.text } -> {
                builder.append(">")
                for (c in children) {
                    c.renderElement(builder, "")
                }
                builder.append("</")
                builder.append(tagName)
            }
            count == 0 -> {
                builder.append("></")
                builder.append(tagName)
            }
            else -> {
                builder.append(">\n")
                val childIndent = indent + "  "
                for (c in children) {
                    c.renderElement(builder, childIndent)
                }
                builder.append(indent)
                builder.append("</")
                builder.append(tagName)
            }
        }
        builder.append('>')

        if (indent.isNotEmpty()) {
            builder.append("\n")
        }
    }

    protected fun renderAttributes(builder: StringBuilder) {
        for (a in attributes.keySet()) {
            val attr = attributes[a]!!
            if (attr.length > 0) {
                builder.append(' ').append(a).append("=\"")
                attr.htmlEscapeTo(builder)
                builder.append("\"")
            }
        }
    }

    public fun attribute(name: String, value: String) {
        attributes[name] = value
    }

    public fun tryGet(attributeName: String): String? {
        return attributes[attributeName]
    }

    public fun hasAttribute(attributeName: String): Boolean {
        return attributes.containsKey(attributeName)
    }

    public fun get(attributeName: String): String {
        val answer = attributes[attributeName]
        if (answer == null) throw RuntimeException("Atrribute $attributeName is missing")
        return answer
    }

    public fun set(attName: String, attValue: String) {
        attributes[attName] = attValue
    }

    /**
     * Override the not operator to add raw content
     */
    public fun String.not(): Unit {
        RawHtml(this@HtmlTag, this)
    }

    /**
     * Override the plus operator to add a text element.
     */
    public fun String.plus() {
        HtmlText(this@HtmlTag, this)
    }

    /**
     * Yet another way to set the text content of the node.
     */
    public var text: String?
        get() {
            if (children.size > 0)
                return children[0].toString()
            return ""
        }
        set(value) {
            children.clear()
            if (value != null)
                HtmlText(this@HtmlTag, value)
        }

}

public open class TransparentTag(containtingTag: HtmlTag?) : HtmlBodyTag(containtingTag, "\$\$transaprent\$\$", contentStyle = ContentStyle.propagate) {
    protected override fun renderElement(builder: StringBuilder, indent: String) {
        for (child in children) {
            child.renderElement(builder, indent)
        }
    }
}

public class RawHtml(containingTag: HtmlTag?, private val html: String) : HtmlElement(containingTag, ContentStyle.text) {
    protected override fun renderElement(builder: StringBuilder, indent: String) {
        if (html.contains("\n")) {
            builder.append(indent)
            builder.append(html)
            if (indent != "")
                builder.append("\n")
        } else {
            builder.append(html)
        }
    }
}

public class HtmlText(containingTag: HtmlTag?, private val text: String) : HtmlElement(containingTag, ContentStyle.text) {
    protected override fun renderElement(builder: StringBuilder, indent: String) {
        builder.append(indent)
        text.htmlEscapeTo(builder)
        if (indent != "")
            builder.append("\n")
    }
}

public class InvalidHtmlException(message: String) : RuntimeException(message) {

}
