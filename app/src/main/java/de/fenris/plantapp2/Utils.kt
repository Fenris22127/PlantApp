package de.fenris.plantapp2

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.data.Room
import de.fenris.plantapp2.data.Sensitivity
import de.fenris.plantapp2.data.WarningSign
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme

class Utils {

    companion object {
        @Composable
        fun roomsToString(room: List<Room>): String {
            val context = LocalContext.current
            return room.joinToString(", ") { it.toString(context) }
        }

        @Composable
        fun WarningSignsToText(warningSigns: List<WarningSign>) {
            warningSigns.forEach { WarningSignToString(warningSign = it) }
        }

        @Composable
        private fun WarningSignToString(warningSign: WarningSign) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(warningSign.sign),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(warningSign.action),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontSize = 16.sp
            )
        }

        @Composable
        fun getRes(res: Int): String {
            return LocalContext.current.getString(res)
        }

        @Composable
        fun getRoom(room: Room): String {
            return when (room) {
                Room.ALL_ROOMS -> stringResource(R.string.room_all_rooms)
                Room.BATHROOM -> stringResource(R.string.room_bathroom)
                Room.LIVING_ROOM -> stringResource(R.string.room_living_room)
                Room.KITCHEN -> stringResource(R.string.room_kitchen)
            }
        }

        @Composable
        fun getSensitivity(sensitivity: Sensitivity): String {
            return when (sensitivity) {
                Sensitivity.INDESTRUCTIBLE -> stringResource(R.string.sensitivity_indestructible)
                Sensitivity.LOW -> stringResource(R.string.sensitivity_low)
                Sensitivity.MEDIUM -> stringResource(R.string.sensitivity_medium)
                Sensitivity.HIGH -> stringResource(R.string.sensitivity_high)
            }
        }

        @Composable
        fun getBackgroundColor(): Color {
            return if (isAppInDarkTheme()) {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            } else {
                Color.White.copy(alpha = 0.8f)
            }
        }

        @Composable
        fun GetBackgroundImage() {
            Box(Modifier.padding(10.dp, 75.dp, 10.dp, 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Background Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    alpha = if (isAppInDarkTheme()) 0.1f else 0.07f
                )
            }
        }

        fun Modifier.shadow(
            color: Color = Color.Black,
            offsetX: Dp = 0.dp,
            offsetY: Dp = 0.dp,
            blurRadius: Dp = 0.dp,
        ) = then(
            drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint()
                    val frameworkPaint = paint.asFrameworkPaint()
                    if (blurRadius != 0.dp) {
                        frameworkPaint.maskFilter =
                            (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
                    }
                    frameworkPaint.color = color.toArgb()

                    val leftPixel = offsetX.toPx()
                    val topPixel = offsetY.toPx()
                    val rightPixel = size.width + topPixel
                    val bottomPixel = size.height + leftPixel

                    canvas.drawRect(
                        left = leftPixel,
                        top = topPixel,
                        right = rightPixel,
                        bottom = bottomPixel,
                        paint = paint,
                    )
                }
            }
        )
    }
}
