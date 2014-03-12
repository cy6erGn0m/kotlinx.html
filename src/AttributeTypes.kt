package kotlinx.html

public abstract class Attribute<T>(val name: String) {
    fun get(tag: HtmlTag, property: PropertyMetadata): T {
        return decode(tag[name]);
    }
    fun set(tag: HtmlTag, property: PropertyMetadata, value: T) {
        tag[name] = encode(value);
    }

    abstract fun encode(t: T): String
    abstract fun decode(s: String): T
}

public open class StringAttribute(name: String) : Attribute<String>(name) {
    override fun encode(t: String): String {
        return t // TODO: it actually might need HTML escaping
    }

    override fun decode(s: String): String {
        return s // TODO: it actually might need decode
    }
}

public class TextAttribute(name: String) : StringAttribute(name)
public class RegexpAttribute(name: String) : StringAttribute(name)
public class IdAttribute(name: String) : StringAttribute(name)
public class MimeAttribute(name: String) : StringAttribute(name)

public class IntAttribute(name: String) : Attribute<Int>(name) {
    override fun encode(t: Int): String {
        return t.toString()
    }

    override fun decode(s: String): Int {
        return s.toInt()
    }
}

public open class BooleanAttribute(name: String, val trueValue: String = "true", val falseValue: String = "false") : Attribute<Boolean>(name) {
    override fun encode(t: Boolean): String {
        return if (t) trueValue else falseValue
    }

    override fun decode(s: String): Boolean {
        return when (s) {
            trueValue -> true
            falseValue -> false
            else -> throw RuntimeException("Unknown value for $name=$s")
        }
    }
}

public class TickerAttribute(name: String) : BooleanAttribute(name, name, "")

public class LinkAttribute(name: String) : Attribute<Link>(name) {
    override fun encode(t: Link): String {
        return t.href()
    }

    override fun decode(s: String): Link {
        return DirectLink(s)
    }
}

public trait StringEnum<T : Enum<T>> : Enum<T> {
    val value: String get() = name()
}

public class EnumAttribute<T : StringEnum<T>>(name: String, val klass: Class<T>) : Attribute<T>(name) {
    override fun encode(t: T): String {
        return t.value
    }

    override fun decode(s: String): T {
        for (c in klass.getEnumConstants()!!) {
            if (encode(c) == s) return c
        }

        throw RuntimeException("Can't decode '$s' as value of '${klass.getName()}'")
    }
}

public class MimeTypesAttribute(name: String) : Attribute<List<String>>(name) {

    override fun encode(t: List<String>): String {
        return t.makeString(",")
    }
    override fun decode(s: String): List<String> {
        return s.split(',').map { it.trim() }
    }
}
