package io.data2viz.viz

import io.data2viz.math.toDegrees
import io.data2viz.path.*
import io.data2viz.path.Rect
import javafx.scene.shape.StrokeLineCap
import kotlin.math.absoluteValue


fun PathNode.render(renderer: JFxVizRenderer) {
    val gc = renderer.gc
    gc.beginPath()
    path.commands.forEach { cmd ->
        when (cmd) {
            is MoveTo -> gc.moveTo(cmd.x, cmd.y)
            is LineTo -> gc.lineTo(cmd.x, cmd.y)
            is QuadraticCurveTo -> gc.quadraticCurveTo(cmd.cpx, cmd.cpy, cmd.x, cmd.y)
            is BezierCurveTo -> gc.bezierCurveTo(cmd.cpx1, cmd.cpy1, cmd.cpx2, cmd.cpy2, cmd.x, cmd.y)
            is Arc -> gc.arc(cmd.centerX, cmd.centerY, cmd.radius, cmd.radius, cmd.start, cmd.length)
            is ArcTo -> gc.arcTo(cmd.fromX, cmd.fromY, cmd.x, cmd.y, cmd.radius)
            is ClosePath -> gc.closePath()
            is Rect -> gc.rect(cmd.x, cmd.y, cmd.w, cmd.h)
            else -> error("Unknown canvas command: ${cmd::class}")
        }
    }

    style.fill?.let {
        gc.fill()
    }

    style.stroke?.let {
        gc.lineCap = StrokeLineCap.BUTT
        gc.stroke()
    }

}

val Arc.start:Double
    get() = -startAngle.toDegrees()

val Arc.length:Double
    get() = (if (counterClockWise) 1 else -1) * (endAngle - startAngle).absoluteValue.toDegrees()