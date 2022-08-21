package com.ankushyerawar.composable_charts.charts

import androidx.compose.ui.graphics.Color

data class ChartProperties(
    val percentList: List<Float>,
    val colorList: List<Color>,
    val showPercentage: Boolean = false,
    val showPercentSymbol: Boolean = true,
    val startAngle: Angle = Angle.ANGLE_270,
    val percentTextColor: Color = Color.White,
    val percentTextSize: Int = Constants.TEXT_SIZE
)
