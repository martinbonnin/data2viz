package io.data2viz.viz


interface Style {
    var fontStyle: FontStyle
}

enum class FontStyle {
    NORMAL, ITALIC, OBLIQUE
}

fun styleSheet(init: StyleSheet.() -> Unit): StyleSheet {
    val styleSheet = StyleSheet()
    styleSheet.init()
    return styleSheet
}

class StyleSheet {
    
    val Serif = FontFamily.Type(FontFamilyType.Serif)
    val SansSerif = FontFamily.Type(FontFamilyType.SansSerif)
    val rules = mutableListOf<CssRule>()
    
    init {
        
        text { 
            fontFamily = SansSerif
            fontSize = 16.px
        }
    }


    fun text(init: CssText.() -> Unit) {
        val textRule = CssText().apply(init)
        rules.add(textRule)
    }
    fun render(): String = rules.joinToString("\n") { it.render()}
}


expect class CssText() : CssRule {
    var fontSize: CssSize? 
    var fontFamily: FontFamily? 
    
    override fun render():String
}

sealed class FontFamily {
    class External(val name:String):FontFamily()
    class Type(val type:FontFamilyType):FontFamily()
}

enum class FontFamilyType(val type: String) {
    Serif("serif"), SansSerif("sans-serif")
}


interface CssRule {
    fun render():String
}


val Number.px
    get() = CssSize(this, CssUnit.PX)

data class CssSize(val number: Number, val unit: CssUnit) {
    override fun toString(): String  = "$number$unit"
}

enum class CssUnit(val asText: String) {
    PX("px"),
    EM("em"),
    PT("pt"),
    PERCENT("%");
    
    override fun toString():String = asText
}