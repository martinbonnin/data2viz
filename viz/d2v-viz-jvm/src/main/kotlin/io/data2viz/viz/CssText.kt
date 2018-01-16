package io.data2viz.viz

actual class CssText : CssRule {
    actual var fontFamily: FontFamily? = null
    actual var fontSize: CssSize? = null
    actual var fontStyle: FontStyle? = null

    actual override fun render(): String {
        return """
        Text {
            ${fontStyle?.let { "-fx-font-style: $it;" }}
            ${fontSize?.let { "-fx-font-size: $it;" }}
            -fx-font-family: sans-serif;
        }
    """
    }
}