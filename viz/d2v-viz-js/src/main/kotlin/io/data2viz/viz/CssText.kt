package io.data2viz.viz

actual class CssText : CssRule {
    actual var fontSize: CssSize? = null
    actual var fontFamily: FontFamily? = null

    actual override fun render(): String {
        return """
        text {
            ${fontSize?.let { "font-size: $it;" }}
            font-family: sans-serif;
        }
        """
    }

}