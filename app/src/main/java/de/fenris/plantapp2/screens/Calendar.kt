package de.fenris.plantapp2.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.ui.theme.getOnSurface
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme
import java.time.LocalDate
import java.time.Month

val current: LocalDate = LocalDate.now()

@Composable
fun CalendarScreen() {
    Box(Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
        Utils.GetBackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    getOnSurface()
                )
                .padding(5.dp)
        ) {
            SetJuly()
            SetAugust()
        }

    }
}

@Composable
fun SetJuly() {
    val julyDates = listOf(
        3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
        28, 29, 30, 31, 1, 2, 3, 4, 5, 6
    )
    Text(
        text = stringResource(R.string.july),
        modifier = Modifier
            .padding(10.dp, 15.dp, 10.dp, 10.dp)
            .fillMaxWidth(),
        fontSize = 22.sp,
        fontWeight = FontWeight(500),
        color = MaterialTheme.colorScheme.onSurface
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
    ) {
        items(julyDates) { date ->
            IconToggleButton(
                content = {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(CircleShape)
                    ) {
                        Text(
                            text = "$date",
                            modifier = Modifier
                                .padding(0.dp, 2.dp, 0.dp, 0.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = if (date > 23) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.5f
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        when (date) {
                            29, 30, 31 -> {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp, 5.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF41c3f7))
                                )
                            }

                            5, 6 -> {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp, 5.dp)
                                        .clip(CircleShape)
                                        .background(Color(0x6641c3f7))
                                )
                            }

                            else -> {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp, 5.dp)
                                        .clip(CircleShape)
                                        .background(Color(0x0041c3f7))
                                )
                            }
                        }
                    }
                },
                checked = date == current.dayOfMonth && current.month == Month.JULY,
                onCheckedChange = { },
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        if (current.dayOfMonth == date && current.month == Month.JULY) MaterialTheme.colorScheme.secondary else Color.Transparent,
                        CircleShape
                    ),
            )
        }
    }
}

@Composable
fun SetAugust() {
    val augustDates = listOf(31, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
    Text(
        text = stringResource(R.string.august),
        modifier = Modifier
            .padding(10.dp, 15.dp, 10.dp, 10.dp)
            .fillMaxWidth(),
        fontSize = 22.sp,
        fontWeight = FontWeight(500),
        color = MaterialTheme.colorScheme.onSurface
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
    ) {
        items(augustDates) { date ->
            IconToggleButton(
                content = {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(CircleShape)
                    ) {
                        Text(
                            text = "$date",
                            modifier = Modifier
                                .padding(0.dp, 2.dp, 0.dp, 0.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = if (date < 31) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.5f
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        when (date) {
                            31 -> {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp, 5.dp)
                                        .clip(CircleShape)
                                        .background(Color(0x6641c3f7))
                                )
                            }

                            5, 6, 7, 12, 13 -> {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp, 5.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF41c3f7))
                                )
                            }

                            else -> {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp, 5.dp)
                                        .clip(CircleShape)
                                        .background(Color(0x0041c3f7))
                                )
                            }
                        }
                    }
                },
                checked = date == current.dayOfMonth && current.month == Month.AUGUST,
                onCheckedChange = { },
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        if (current.dayOfMonth == date && current.month == Month.AUGUST) MaterialTheme.colorScheme.secondary else Color.Transparent,
                        CircleShape
                    ),
            )
        }
    }
}