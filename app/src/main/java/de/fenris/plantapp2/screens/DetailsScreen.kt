package de.fenris.plantapp2.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.ReportProblem
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.data.Plant
import de.fenris.plantapp2.data.WarningSign
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(plant: Plant) {
    Column(
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .verticalScroll(
                rememberScrollState()
            )
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            ImageSlider(
                listOf(plant.coverImage, plant.myImage)
            )
        }
        DetailFields(plant)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<Any>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        2
    }
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 7.dp, 0.dp, 5.dp)
    ) {
        Box(modifier = Modifier
            .width(280.dp)
            .height(280.dp)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .width(280.dp),
                pageSize = PageSize.Fill
            ) { page ->
                Image(
                    painter = painterResource(id = images[page] as Int),
                    contentDescription = "Plant",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .offset(if (pagerState.currentPage == 0) -(5).dp else 5.dp, 0.dp)
                    .fillMaxWidth(0.1f)
                    .fillMaxHeight(0.1f)
                    .clip(CircleShape)
                    .background(Color(0xd9CACACA))
                    .padding(2.dp, 0.dp, 0.dp, 0.dp)
                    .align(if (pagerState.currentPage == 0) Alignment.CenterEnd else Alignment.CenterStart)

            ) {
                if (pagerState.currentPage == 0) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Go to next image",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage - 1
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Go to previous image",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(0.dp, 0.dp, 2.dp, 0.dp)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                imageVector = (if (pagerState.currentPage == 0) Icons.Filled.Circle else Icons.Outlined.Circle),
                contentDescription = "Image 1 Display State",
                modifier = Modifier
                    .size(18.dp)
                    .padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
            Icon(
                imageVector = (if (pagerState.currentPage == 0) Icons.Outlined.Circle else Icons.Filled.Circle),
                contentDescription = "Image 2 Display State",
                modifier = Modifier
                    .size(18.dp)
                    .padding(5.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }
}

@Composable
fun DetailFields(plant: Plant) {
    Column {
        Text(
            text = stringResource(plant.name),
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 30.sp,
            fontWeight = FontWeight(400)
        )
        Text(
            text = plant.latinName,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetDetailCard(
            R.drawable.ic_room,
            "Rooms Icon",
            stringResource(R.string.rooms),
            Utils.roomsToString(plant.room)
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetDetailIconCard(
            Icons.Outlined.WaterDrop,
            "Water Amount Icon",
            stringResource(R.string.water_amount),
            "${plant.water} ml"
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetDetailCard(
            R.drawable.ic_water_frequency,
            "Water Frequency Icon",
            stringResource(R.string.water_frequency),
            stringResource(plant.waterFrequency)
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetDetailCard(
            R.drawable.ic_note,
            "Note Icon",
            stringResource(R.string.note),
            if (stringResource(plant.note) == "\"\"") "" else stringResource(plant.note)
        )
        Spacer(modifier = Modifier.height(10.dp))
        GetWarningCard(plant.warningSigns)
        Spacer(modifier = Modifier.height(10.dp))
        GetDetailIconCard(
            Icons.Outlined.Shield,
            "Sensitivity Icon",
            stringResource(R.string.sensitivity),
            Utils.getSensitivity(plant.sensitivity)
        )
    }
}

@Composable
fun GetDetailIconCard(
    vector: ImageVector,
    iconDescription: String,
    header: String,
    details: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .padding(7.dp, 10.dp, 10.dp, 10.dp)
    ) {
        Icon(
            vector,
            contentDescription = iconDescription,
            modifier = Modifier
                .size(33.dp)
                .padding(PaddingValues(0.dp, 0.dp, 7.dp, 0.dp)),
            tint = MaterialTheme.colorScheme.onSurface,
        )
        Column {
            Text(
                text = header,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = details,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun GetDetailCard(iconId: Int, iconDescription: String, header: String, details: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .padding(10.dp, 10.dp, 10.dp, 10.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = iconDescription,
            modifier = Modifier
                .size(33.dp)
                .padding(
                    if (iconId == R.drawable.ic_water) PaddingValues(
                        3.dp,
                        0.dp,
                        13.dp,
                        0.dp
                    ) else PaddingValues(0.dp, 0.dp, 10.dp, 0.dp)
                ),
            tint = if (isAppInDarkTheme()) Color.White else Color.Black
        )
        Column {
            Text(
                text = header,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = details,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun GetWarningCard(warningSigns: List<WarningSign>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            .padding(7.dp, 10.dp, 7.dp, 10.dp)
    ) {
        Icon(
            Icons.Outlined.ReportProblem,
            contentDescription = "Warning Signs Icon",
            modifier = Modifier
                .size(33.dp)
                .padding(0.dp, 0.dp, 10.dp, 0.dp),
            tint = if (isAppInDarkTheme()) Color.White else Color.Black
        )
        Column {
            Text(
                text = stringResource(R.string.warning_signs),
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Utils.WarningSignsToText(warningSigns)
        }
    }
}