package io.data2viz.viz

actual class CssText : CssRule {
    actual var fontSize: CssSize? = null
    actual var fontStyle: FontStyle? = null
    actual var fontFamily: FontFamily? = null

    actual override fun render(): String {
        return """
        text {
            ${fontSize?.let { "font-size: $it;" }}
            ${fontStyle?.let { "font-style: $it;" }}
            
            font-family: sans-serif;
        }
        """
    }

}