package com.ankushyerawar.composable_charts.charts

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ankushyerawar.composable_charts.charts.Constants.Companion.ADDED_WIDTH
import com.ankushyerawar.composable_charts.charts.Constants.Companion.ARC_DIVIDER
import com.ankushyerawar.composable_charts.charts.Constants.Companion.EXTRA_WEIGHT
import com.ankushyerawar.composable_charts.charts.Constants.Companion.SHOW_TEXT_ANGLE
import com.ankushyerawar.composable_charts.charts.Constants.Companion.START_ANGLE_0
import com.ankushyerawar.composable_charts.charts.Constants.Companion.START_ANGLE_180
import com.ankushyerawar.composable_charts.charts.Constants.Companion.START_ANGLE_270
import com.ankushyerawar.composable_charts.charts.Constants.Companion.START_ANGLE_90
import com.ankushyerawar.composable_charts.charts.Constants.Companion.TEXT_SIZE
import com.ankushyerawar.composable_charts.charts.Constants.Companion.TOTAL_ANGLE_DEGREE
import com.ankushyerawar.composable_charts.charts.Constants.Companion.TOTAL_PERCENT
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

const val PERCENT_SYMBOL = "%"

@Composable
fun PieChart(
    modifier: Modifier,
    properties: ChartProperties
) {
    val totalPercentage = properties.percentList.sum()
    val percentAngle = properties.percentList.map {
        it * TOTAL_PERCENT / totalPercentage
    }
    val sweepAngle = percentAngle.map {
        it * TOTAL_ANGLE_DEGREE / TOTAL_PERCENT
    }
    var angle = getAngle(properties.startAngle)

    BoxWithConstraints(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {

        Canvas(
            modifier = Modifier
                .size(maxWidth, maxWidth)
        ) {

            for ((index, percent) in properties.percentList.withIndex()) {
                // Draws the pie chart
                drawArc(
                    color = properties.colorList[index],
                    startAngle = angle,
                    sweepAngle = sweepAngle[index],
                    useCenter = true,
                    size = Size(size.width, size.width)
                )

                if (properties.showPercentage && percent >= SHOW_TEXT_ANGLE) {
                    // Calculate the acr center by measuring the startAngle and middle of sweepAngle
                    val arcCenter = angle + (sweepAngle[index] / ARC_DIVIDER)

                    // Calculate the x & y co-ordinates to show the percentage text
                    val x = getTextXPosition(properties.percentTextSize, maxWidth, arcCenter, size)
                    val y = getTextYPosition(properties.percentTextSize, maxWidth, arcCenter, size)

                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            "${percent.roundToInt()}${showPercentSymbol(properties.showPercentSymbol)}",
                            x.toFloat(),
                            y.toFloat(),
                            Paint().apply {
                                isAntiAlias = true
                                textSize = properties.percentTextSize.sp.toPx()
                                textAlign = Paint.Align.CENTER
                                color = properties.percentTextColor.toArgb()
                            }
                        )
                    }
                }

                angle += sweepAngle[index]
            }
        }
    }
}

private fun getAngle(angle: Angle): Float {
    return when (angle) {
        Angle.ANGLE_0 -> START_ANGLE_0
        Angle.ANGLE_90 -> START_ANGLE_90
        Angle.ANGLE_180 -> START_ANGLE_180
        else -> START_ANGLE_270
    }
}

private fun getTextXPosition(
    textSize: Int,
    totalWidth: Dp,
    arcCenter: Float,
    size: Size,
    extraWeight: Int = EXTRA_WEIGHT
): Double {
    var textWeight = extraWeight
    if (textSize != TEXT_SIZE) {
        textWeight = ADDED_WIDTH
    }
    return (totalWidth + ADDED_WIDTH.dp / ARC_DIVIDER).value * cos(
        Math.toRadians(
            arcCenter.toDouble()
        )
    ) + size.center.x + textWeight
}

private fun getTextYPosition(
    textSize: Int,
    totalWidth: Dp,
    arcCenter: Float,
    size: Size,
    extraWeight: Int = EXTRA_WEIGHT
): Double {
    var textWeight = extraWeight
    if (textSize != TEXT_SIZE) {
        textWeight = ADDED_WIDTH
    }
    return (totalWidth + ADDED_WIDTH.dp / ARC_DIVIDER).value * sin(
        Math.toRadians(
            arcCenter.toDouble()
        )
    ) + size.center.y + textWeight
}

private fun showPercentSymbol(show: Boolean): String {
    return if (show) {
        PERCENT_SYMBOL
    } else {
        ""
    }
}



