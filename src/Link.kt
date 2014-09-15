package kotlinx.html

public trait Link {
    public fun href(): String
}

public class DirectLink(private val href: String) : Link {
    public override fun href() = href
}
