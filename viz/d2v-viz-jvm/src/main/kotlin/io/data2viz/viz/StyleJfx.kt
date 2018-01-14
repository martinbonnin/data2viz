package io.data2viz.viz

import javafx.scene.Scene
import java.net.MalformedURLException
import java.net.URL


class StyleJfx : Style {

    val sb = StringBuilder()

    override var fontStyle: FontStyle
        get() = TODO("not implemented")
        set(value) {
            sb.append("-fx-font-style:${value.name.toLowerCase()}")
        }

}


/**
 * Apply the stylesheet to current scene
 */
fun applyStylesheet(scene: Scene, styleSheet: StyleSheet) {
    scene.stylesheets.add(styleSheet.url)
}

