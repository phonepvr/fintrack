package com.fintrack.ui.home.overview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.fintrack.domain.util.formatIndianCurrency
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode

data class ChartSeries(
    val name: String,
    val color: Color,
    val points: List<Pair<LocalDate, BigDecimal>>,
)

/**
 * Compose-Canvas line chart. Multi-series, axis labels, optional legend.
 *
 * Simpler than wiring Vico's 2.x compose pipeline; the Phase-4 use cases
 * (4 chart variants × 4 period filters) are basic enough that a hand-rolled
 * Canvas chart is the lower-risk choice.
 */
@Composable
fun LineChart(
    series: List<ChartSeries>,
    modifier: Modifier = Modifier,
    valueFormatter: (BigDecimal) -> String = { formatIndianCurrency(it) },
) {
    val onSurface = MaterialTheme.colorScheme.onSurface
    val outlineSoft = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    val density = LocalDensity.current
    val labelTextPx = with(density) { 11.dp.toPx() }
    val gridStrokePx = with(density) { 1.dp.toPx() }
    val lineStrokePx = with(density) { 2.dp.toPx() }
    val pointRadiusPx = with(density) { 3.dp.toPx() }
    val leftPad = with(density) { 56.dp.toPx() }
    val bottomPad = with(density) { 28.dp.toPx() }
    val rightPad = with(density) { 12.dp.toPx() }
    val topPad = with(density) { 8.dp.toPx() }

    val yLabelPaint = remember(onSurfaceVariant, labelTextPx) {
        android.graphics.Paint().apply {
            color = onSurfaceVariant.toArgb()
            textSize = labelTextPx
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.RIGHT
        }
    }
    val xLabelLeftPaint = remember(onSurfaceVariant, labelTextPx) {
        android.graphics.Paint().apply {
            color = onSurfaceVariant.toArgb()
            textSize = labelTextPx
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.LEFT
        }
    }
    val xLabelRightPaint = remember(onSurfaceVariant, labelTextPx) {
        android.graphics.Paint().apply {
            color = onSurfaceVariant.toArgb()
            textSize = labelTextPx
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.RIGHT
        }
    }

    val flatPoints = series.flatMap { it.points }
    if (flatPoints.isEmpty()) {
        Box(
            modifier = modifier.fillMaxWidth().height(220.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "Not enough data to chart yet.",
                color = onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        return
    }

    val minY = flatPoints.minOf { it.second }
    val rawMaxY = flatPoints.maxOf { it.second }
    val maxY = if (rawMaxY == minY) rawMaxY.add(BigDecimal.ONE) else rawMaxY
    val minX = flatPoints.minOf { it.first.toEpochDays().toLong() }
    val rawMaxX = flatPoints.maxOf { it.first.toEpochDays().toLong() }
    val maxX = if (rawMaxX == minX) rawMaxX + 1 else rawMaxX

    Column(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
        ) {
            val width = size.width
            val height = size.height
            val plotLeft = leftPad
            val plotTop = topPad
            val plotRight = width - rightPad
            val plotBottom = height - bottomPad
            val plotW = plotRight - plotLeft
            val plotH = plotBottom - plotTop
            val yRange = (maxY - minY).coerceAtLeast(BigDecimal.ONE)

            val yTicks = 4
            for (i in 0..yTicks) {
                val frac = i.toFloat() / yTicks
                val y = plotBottom - frac * plotH
                drawLine(
                    color = outlineSoft,
                    start = Offset(plotLeft, y),
                    end = Offset(plotRight, y),
                    strokeWidth = gridStrokePx,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f)),
                )
                val tickValue = minY.add(yRange.multiply(BigDecimal.valueOf(frac.toDouble())))
                    .setScale(0, RoundingMode.HALF_UP)
                drawContext.canvas.nativeCanvas.drawText(
                    valueFormatter(tickValue),
                    plotLeft - 6f,
                    y + labelTextPx / 3f,
                    yLabelPaint,
                )
            }

            drawContext.canvas.nativeCanvas.drawText(
                LocalDate.fromEpochDays(minX.toInt()).toString(),
                plotLeft,
                plotBottom + bottomPad * 0.7f,
                xLabelLeftPaint,
            )
            drawContext.canvas.nativeCanvas.drawText(
                LocalDate.fromEpochDays(maxX.toInt()).toString(),
                plotRight,
                plotBottom + bottomPad * 0.7f,
                xLabelRightPaint,
            )

            for (s in series) {
                if (s.points.isEmpty()) continue
                val sorted = s.points.sortedBy { it.first }
                val path = Path()
                sorted.forEachIndexed { idx, (date, value) ->
                    val p = toPx(
                        date = date,
                        value = value,
                        minX = minX, maxX = maxX,
                        minY = minY, yRange = yRange,
                        plotLeft = plotLeft, plotW = plotW,
                        plotBottom = plotBottom, plotH = plotH,
                    )
                    if (idx == 0) path.moveTo(p.x, p.y) else path.lineTo(p.x, p.y)
                }
                drawPath(
                    path = path,
                    color = s.color,
                    style = Stroke(width = lineStrokePx, cap = StrokeCap.Round),
                )
                sorted.forEach { (date, value) ->
                    val p = toPx(
                        date = date,
                        value = value,
                        minX = minX, maxX = maxX,
                        minY = minY, yRange = yRange,
                        plotLeft = plotLeft, plotW = plotW,
                        plotBottom = plotBottom, plotH = plotH,
                    )
                    drawCircle(color = s.color, radius = pointRadiusPx, center = p)
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Legend(series = series.filter { it.points.isNotEmpty() }, onSurface = onSurface)
    }
}

@Suppress("LongParameterList")
private fun toPx(
    date: LocalDate,
    value: BigDecimal,
    minX: Long,
    maxX: Long,
    minY: BigDecimal,
    yRange: BigDecimal,
    plotLeft: Float,
    plotW: Float,
    plotBottom: Float,
    plotH: Float,
): Offset {
    val days = date.toEpochDays().toLong()
    val xFrac = if (maxX == minX) 0.5f else (days - minX).toFloat() / (maxX - minX).toFloat()
    val yFrac = (value - minY).toFloat() / yRange.toFloat()
    return Offset(
        x = plotLeft + xFrac * plotW,
        y = plotBottom - yFrac * plotH,
    )
}

@Composable
private fun Legend(series: List<ChartSeries>, onSurface: Color) {
    if (series.size <= 1) return
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        series.forEach { s ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(s.color),
                )
                Spacer(Modifier.size(6.dp))
                Text(
                    s.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = onSurface,
                )
            }
        }
    }
}
