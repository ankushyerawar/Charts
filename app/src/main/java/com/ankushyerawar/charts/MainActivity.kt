package com.ankushyerawar.charts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.ankushyerawar.charts.MainActivity.Companion.ROW_WEIGHT
import com.ankushyerawar.charts.MainActivity.Companion.TEXT_SIZE
import com.ankushyerawar.charts.ui.theme.BlueGray600
import com.ankushyerawar.charts.ui.theme.ChartsTheme
import com.ankushyerawar.charts.ui.theme.Green800
import com.ankushyerawar.charts.ui.theme.Indigo600
import com.ankushyerawar.charts.ui.theme.Orange800
import com.ankushyerawar.charts.ui.theme.Purple200
import com.ankushyerawar.composable_charts.charts.Angle
import com.ankushyerawar.composable_charts.charts.PieChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartsTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_big))
                    ) {
                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            percentList = getPercentageList(),
                            colorList = getColorList(),
                            showPercentage = true
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_big),
                                start = dimensionResource(id = R.dimen.padding_start_end_medium),
                                end = dimensionResource(id = R.dimen.padding_start_end_medium)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = dimensionResource(id = R.dimen.padding_start_end_small))
                                .wrapContentHeight()
                                .weight(ROW_WEIGHT),
                            percentList = getPercentageList(),
                            colorList = getColorList(),
                            showPercentage = true,
                            startAngle = Angle.ANGLE_0,
                            percentTextSize = TEXT_SIZE
                        )

                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = dimensionResource(id = R.dimen.padding_start_end_small))
                                .weight(ROW_WEIGHT),
                            percentList = getPercentageList(),
                            colorList = getColorList(),
                            showPercentage = true,
                            startAngle = Angle.ANGLE_0,
                            percentTextSize = TEXT_SIZE
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val ROW_WEIGHT = 1f
        const val TEXT_SIZE = 12
    }
}

@Preview(showBackground = true)
@Composable
fun Renderer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_big))
        ) {
            PieChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                percentList = getPercentageList(),
                colorList = getColorList(),
                showPercentage = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_big),
                    start = dimensionResource(id = R.dimen.padding_start_end_medium),
                    end = dimensionResource(id = R.dimen.padding_start_end_medium)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PieChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = dimensionResource(id = R.dimen.padding_start_end_small))
                    .wrapContentHeight()
                    .weight(ROW_WEIGHT),
                percentList = getPercentageList(),
                colorList = getColorList(),
                showPercentage = true,
                startAngle = Angle.ANGLE_0,
                percentTextSize = TEXT_SIZE
            )

            PieChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = dimensionResource(id = R.dimen.padding_start_end_small))
                    .weight(ROW_WEIGHT),
                percentList = getPercentageList(),
                colorList = getColorList(),
                showPercentage = true,
                showPercentSymbol = false,
                startAngle = Angle.ANGLE_0,
                percentTextSize = TEXT_SIZE
            )
        }
    }
}

private fun getPercentageList(): List<Float> {
    return listOf(40f, 25f, 10f, 5f, 20f).shuffled()
}

private fun getColorList(): List<Color> {
    return listOf(
        Indigo600,
        Green800,
        Orange800,
        Purple200,
        BlueGray600
    )
}