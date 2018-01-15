package io.data2viz.viz

actual class CssText : CssRule {
    actual var fontSize: CssSize? = null
    actual var fontFamily: FontFamily? = null

    actual override fun render(): String {
        return """
        Text {
            ${fontSize?.let { "-fx-font-size: $it;" }}
            -fx-font-family: sans-serif;
        }
        
    """
    }
}