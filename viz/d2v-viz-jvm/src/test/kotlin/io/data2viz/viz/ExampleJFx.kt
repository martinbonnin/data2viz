package io.data2viz.viz

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApp : Application() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(HelloApp::class.java)
        }
    }

    override fun start(primaryStage: Stage?) {
        val root = Group()
        root.viz {
            commonViz(data, StyleSheet())
        }

        primaryStage?.let {
            it.scene = (Scene(root, 500.0, 500.0))
            it.show()
        }
    }
}

val data = listOf(
    Domain(10.0, 10.0),
    Domain(20.0, 40.0),
    Domain(30.0, 90.0)
)
