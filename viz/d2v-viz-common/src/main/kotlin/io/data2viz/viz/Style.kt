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
    

    val rules = mutableListOf<CssRule>()

    fun text(init: CssText.() -> Unit) {
        val textRule = CssText().apply(init)
        rules.add(textRule)
    }
    fun render(): String = """
        
        text {
            font-family: sans-serif;
            font-size: 24px;
        }
        
        Text {
            -fx-font-size: 32px;
            -fx-font-family: sans-serif;
            -fx-font-style: normal ;
            
        }
        
        .root {
            -fx-font-size: 24px;
            -fx-font-family: serif;
            -fx-font-style: italic ;
            -fx-base: rgb(132, 145, 47);
            -fx-background: rgb(225, 228, 203);
        }
        """
}


class CssText : CssRule {
    var fontSize: CssSize? = null
    var fontFamily: String? = null
}

interface CssRule


val Number.px
    get() = CssSize(this, CssUnit.PX)

data class CssSize(val number: Number, val unit: CssUnit)

enum class CssUnit(val asText: String) {
    PX("px"),
    EM("em"),
    PT("pt"),
    PERCENT("%")
}