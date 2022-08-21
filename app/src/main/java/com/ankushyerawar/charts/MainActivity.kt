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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ankushyerawar.charts.ui.theme.BlueGray600
import com.ankushyerawar.charts.ui.theme.ChartsTheme
import com.ankushyerawar.charts.ui.theme.Green800
import com.ankushyerawar.charts.ui.theme.Indigo600
import com.ankushyerawar.charts.ui.theme.Orange800
import com.ankushyerawar.charts.ui.theme.Purple200
import com.ankushyerawar.charts.ui.theme.Purple500
import com.ankushyerawar.composable_charts.charts.Angle
import com.ankushyerawar.composable_charts.charts.ChartProperties
import com.ankushyerawar.composable_charts.charts.PieChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val properties = ChartProperties(
            percentList = getPercentageList(),
            colorList = getColorList()
        )
        setContent {
            ChartsTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(MaterialTheme.colors.background)
                        .padding(bottom = dimensionResource(id = R.dimen.padding_big))
                ) {
                    PieHeading(stringResource(id = R.string.normal_pie_chart))

                    Box(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.padding_big),
                                end = dimensionResource(id = R.dimen.padding_big)
                            )
                    ) {
                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            properties = properties
                        )
                    }

                    PieHeading(stringResource(id = R.string.text_pie_chart))

                    Box(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.padding_big),
                                end = dimensionResource(id = R.dimen.padding_big)
                            )
                    ) {
                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            properties = properties.copy(
                                showPercentage = true,
                                showPercentSymbol = false
                            )
                        )
                    }

                    PieHeading(stringResource(id = R.string.symbol_pie_chart))

                    Box(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.padding_big),
                                end = dimensionResource(id = R.dimen.padding_big)
                            )
                    ) {
                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            properties = properties.copy(
                                showPercentage = true
                            )
                        )
                    }

                    PieHeading(stringResource(id = R.string.percent_pie_small))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.padding_big),
                                end = dimensionResource(id = R.dimen.padding_big)
                            ),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = dimensionResource(id = R.dimen.padding_start_end_small))
                                .wrapContentHeight()
                                .weight(ROW_WEIGHT),
                            properties = properties.copy(
                                showPercentage = true,
                                startAngle = Angle.ANGLE_0,
                                percentTextSize = TEXT_SIZE
                            )
                        )

                        PieChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = dimensionResource(id = R.dimen.padding_start_end_small))
                                .weight(ROW_WEIGHT),
                            properties = properties.copy(
                                showPercentage = true,
                                showPercentSymbol = false,
                                startAngle = Angle.ANGLE_90,
                                percentTextSize = TEXT_SIZE
                            )
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

@Composable
fun PieHeading(heading: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.padding_big))
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = heading,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Purple500,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = dimensionResource(id = R.dimen.heading_text).value.sp
            )
        )
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