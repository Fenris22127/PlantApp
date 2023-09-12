package de.fenris.plantapp2.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.ReportProblem
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.data.Plant
import de.fenris.plantapp2.data.WarningSign
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme
import kotlinx.coroutines.launch

private const val maxListSize: Int = 3

@Composable
fun DetailsScreen(
    plant: Plant
) {
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
                plant,
                listOf(plant.coverImage) + plant.myImage
            )
        }
        DetailFields(plant)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    plant: Plant,
    images: List<Int>
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        images.size
    }
    val sizeList = remember { createList(images.size) }
    val startRange = remember { mutableIntStateOf(0) }
    val endRange =
        remember { mutableIntStateOf(if (images.size <= maxListSize) images.size - 1 else maxListSize - 1) }
    val currentPage = remember { mutableIntStateOf(pagerState.currentPage) }
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 7.dp, 0.dp, 5.dp)
    ) {
        Box(
            modifier = Modifier
                .width(280.dp)
                .height(280.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .width(280.dp),
                pageSize = PageSize.Fill
            ) { page ->
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "${plant.name} #${page + 1}",
                    modifier = Modifier
                        .fillMaxSize()
                )
                if (page < startRange.intValue) {
                    startRange.intValue -= 1
                    endRange.intValue -= 1
                }
                if (page > endRange.intValue) {
                    startRange.intValue += 1
                    endRange.intValue += 1
                }
                currentPage.intValue = page
                adjustList(sizeList, page, startRange, endRange)
            }
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "${pagerState.currentPage + 1}/${images.size}",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF303030).copy(alpha = 0.5f))
                        .padding(5.dp, 3.dp),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1
                            )
                        }

                        currentPage.intValue = currentPage.intValue - 1
                        if (currentPage.intValue < startRange.intValue) {
                            startRange.intValue -= 1
                            endRange.intValue -= 1
                        }
                        adjustList(sizeList, currentPage.intValue, startRange, endRange)
                    },
                    modifier = Modifier
                        .alpha(if (pagerState.currentPage == 0) 0f else 1f)
                        .offset(5.dp, 0.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == 0) Color.Transparent else Color(
                                0xd9CACACA
                            )
                        )
                        .padding(0.dp, 0.dp, 2.dp, 0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Go to previous image",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }

                        currentPage.intValue = currentPage.intValue + 1
                        if (currentPage.intValue > endRange.intValue) {
                            startRange.intValue += 1
                            endRange.intValue += 1
                        }
                        adjustList(sizeList, currentPage.intValue, startRange, endRange)
                    },
                    modifier = Modifier
                        .alpha(if (pagerState.currentPage == images.size - 1) 0f else 1f)
                        .offset((-5).dp, 0.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == images.size - 1) Color.Transparent else Color(
                                0xd9CACACA
                            )
                        )
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Go to next image",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    )
                }
            }
        }
        LazyRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(images) { index, _ ->
                println("Index $index")
                if (sizeList[index] != 3) {
                    GetCircle(
                        circleNr = index,
                        currentPage = pagerState.currentPage,
                        sizeList = sizeList
                    )
                }
            }
        }
    }
}

fun adjustList(
    list: MutableList<Int>,
    currentPage: Int,
    startRange: MutableIntState,
    endRange: MutableIntState
): MutableList<Int> {
    var adjustingEndIndex = endRange.intValue
    var adjustingStartIndex = startRange.intValue
    list[currentPage] = 0
    if (adjustingEndIndex + 1 < list.size) {
        list[adjustingEndIndex + 1] = 1
        adjustingEndIndex += 1
    }
    if (adjustingEndIndex + 1 < list.size) {
        list[adjustingEndIndex + 1] = 2
        adjustingEndIndex += 1
    }
    for (j in adjustingEndIndex + 1 until list.size) {
        list[j] = 3
    }
    if (adjustingStartIndex - 1 >= 0) {
        list[adjustingStartIndex - 1] = 1
        adjustingStartIndex -= 1
    }
    if (adjustingStartIndex - 1 >= 0) {
        list[adjustingStartIndex - 1] = 2
        adjustingStartIndex -= 1
    }
    for (j in adjustingEndIndex + 1 until list.size) {
        list[j] = 3
    }
    for (j in adjustingStartIndex - 1 downTo 0) {
        list[j] = 3
    }
    return list
}

fun createList(listSize: Int): MutableList<Int> {
    val sizeList = mutableListOf(0)
    if (listSize <= maxListSize) {
        for (i in 1..listSize) {
            sizeList.add(0)
        }
    }
    if (listSize == maxListSize + 1) {
        for (i in 1 until maxListSize) {
            sizeList.add(0)
        }
        sizeList.add(1)
    }
    if (listSize == maxListSize + 2) {
        for (i in 1 until maxListSize) {
            sizeList.add(0)
        }
        sizeList.add(1)
        sizeList.add(2)
    }
    if (listSize >= maxListSize + 3) {
        for (i in 1 until maxListSize) {
            sizeList.add(0)
        }
        sizeList.add(1)
        sizeList.add(2)
        for (i in maxListSize + 2 until listSize) {
            sizeList.add(3)
        }
    }
    return sizeList
}

@Composable
fun GetCircle(circleNr: Int, currentPage: Int, sizeList: MutableList<Int>) {
    Icon(
        imageVector = (getCircleIcon(currentPage, circleNr, sizeList)),
        contentDescription = "Image 1 Display State",
        modifier = Modifier
            .size(
                getCircleSize(circleNr, sizeList)
            )
            .padding(0.dp, 0.dp, 5.dp, 0.dp),
        tint = MaterialTheme.colorScheme.onSurface,
    )
}

fun getCircleSize(circleNr: Int, sizeList: MutableList<Int>): Dp {
    return when (sizeList[circleNr]) {
        1 -> 14.dp
        2 -> 10.dp
        else -> 18.dp
    }
}

fun getCircleIcon(currentPage: Int, circleNr: Int, sizeList: MutableList<Int>): ImageVector {
    return if (currentPage == circleNr || sizeList[circleNr] == 2) Icons.Filled.Circle
    else Icons.Outlined.Circle
}

@Composable
fun DetailFields(plant: Plant) {
    val context = LocalContext.current
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
            context.getString(plant.water)
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