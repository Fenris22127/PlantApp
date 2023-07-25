package de.fenris.plantapp2.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeRepairService
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.ui.theme.getOnSurface
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    val maxScroll = remember {
        mutableIntStateOf(scrollState.maxValue)
    }
    Box(
        Modifier
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
    ) {
        Utils.GetBackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            //SliderTest()

            GetKeyHandover()
            Spacer(modifier = Modifier.height(20.dp))
            GetInfo()
            Spacer(modifier = Modifier.height(20.dp))
            GetMoreLessInfo()
            Spacer(modifier = Modifier.height(20.dp))
            HomeDetails()
            Spacer(modifier = Modifier.height(20.dp))
            FlipCard(maxScroll, scrollState)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
/*val maxListSize: Int = 3
@Composable
fun SliderTest(
) {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val sizeList = remember { createList(list.size) }
    val startRange = remember { mutableIntStateOf(0) }
    val endRange = remember { mutableIntStateOf(if (list.size < maxListSize) list.size - 1 else maxListSize - 1) }
    val currentPage = remember { mutableIntStateOf(0) }

    Text(text = sizeList.toString())
    Text(text = "Position: ${currentPage.intValue}")
    Text(text = "Start: ${startRange.intValue}")
    Text(text = "End: ${endRange.intValue}")

    Row {
        Button(
            onClick = {
                currentPage.intValue = currentPage.intValue - 1
                if(currentPage.intValue < startRange.intValue) {
                    startRange.intValue -= 1
                    endRange.intValue -= 1
                }
                println(sizeList)
                adjustList(sizeList, currentPage, startRange, endRange)
                      },
            enabled = currentPage.intValue > 0,
            content = {
                Text(text = "Previous")
            }
        )
        Button(
            onClick = {
                currentPage.intValue = currentPage.intValue + 1
                if(currentPage.intValue > endRange.intValue) {
                    startRange.intValue += 1
                    endRange.intValue += 1
                }
                println(sizeList)
                adjustList(sizeList, currentPage, startRange, endRange)
                      },
            enabled = currentPage.intValue < list.size - 1,
            content = {
                Text(text = "Next")
            }
        )
    }

    LazyRow(modifier = Modifier
        .padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(list) { index, _ ->
            if (sizeList[index] != 3) {
                GetCircle(circleNr = index, currentPage = currentPage.intValue, sizeList =  sizeList)
            }
        }
    }

}

fun adjustList(list: MutableList<Int>, currentPage: MutableIntState, startRange: MutableIntState, endRange: MutableIntState) : MutableList<Int> {
    var adjustingEndIndex = endRange.intValue
    var adjustingStartIndex = startRange.intValue
    list[currentPage.intValue] = 0
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
    println("List: $list")
    return list
}

fun createList(listSize : Int) : MutableList<Int> {
    val sizeList = mutableListOf(0)
    if (listSize == maxListSize) sizeList.fill(0)
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
fun GetCircle(circleNr : Int, currentPage : Int, sizeList : MutableList<Int>) {
    androidx.compose.material.Icon(
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
    return when(sizeList[circleNr]) {
        1 -> 14.dp
        2 -> 10.dp
        else -> 18.dp
    }
}

fun getCircleIcon(currentPage : Int, circleNr: Int, sizeList: MutableList<Int>): ImageVector {
    return if (currentPage == circleNr || sizeList[circleNr] == 2 ) Icons.Filled.Circle
    else Icons.Outlined.Circle
}*/

@Composable
fun GetKeyHandover() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = getOnSurface()
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Key,
                contentDescription = "Key Icon",
                modifier = Modifier
                    .size(40.dp)
                    .scale(scaleX = -1f, scaleY = 1f)
                    .rotate(45.0f),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = stringResource(R.string.handing_over_the_key),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(0.dp, 0.dp, 0.dp, 5.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string._when),
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .width(65.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Text(
                text = stringResource(R.string.between_31_07_and_06_08),
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontSize = 16.sp,
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(0.dp, 0.dp, 0.dp, 5.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.from),
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .width(65.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Text(
                text = "Malte",
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontSize = 16.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    text = stringResource(R.string.key_return),
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .width(65.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Text(
                text = stringResource(R.string.key_return_text),
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
fun GetInfo() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = getOnSurface()
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = stringResource(R.string.my_trip),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.return_date),
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Text(
                text = stringResource(R.string.return_date_details),
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontSize = 16.sp,
            )
        }
        Text(
            text = stringResource(id = R.string.my_trip_para1),
            modifier = Modifier
                .padding(10.dp, 5.dp),
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = R.string.my_trip_para2),
            modifier = Modifier
                .padding(10.dp, 0.dp),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
fun GetMoreLessInfo() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = getOnSurface()
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.HomeRepairService,
                contentDescription = "Tools & Co. Icon",
                modifier = Modifier
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = stringResource(R.string.tools_co),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row {
            Text(
                text = stringResource(R.string.watering_can),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Column {
                Text(
                    text = stringResource(R.string.under_the_kitchen_table),
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 16.sp,
                )
                Text(
                    text = stringResource(R.string.watering_can_details),
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.spray_bottle),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text = stringResource(R.string.on_the_kitchen_table),
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.measuring_cup),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text = stringResource(R.string.on_the_kitchen_table),
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.instructions),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text = stringResource(R.string.on_the_kitchen_table),
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row {
            Text(
                text = stringResource(R.string.frame),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Column {
                Text(
                    text = stringResource(R.string.on_the_kitchen_table),
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 16.sp,
                )
                Text(
                    text = stringResource(R.string.it_turns_on_and_off_by_itself_so_don_t_worry_if_it_s_on),
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.frame_remote),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text = stringResource(R.string.on_the_kitchen_table),
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row(verticalAlignment = Alignment.Top) {
            Text(
                text = stringResource(R.string.moisture_meter),
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Column {
                Text(
                    text = stringResource(R.string.on_the_kitchen_table),
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 16.sp,
                )
                Text(
                    text = stringResource(R.string.moisture_meter_details),
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
        }
        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
fun HomeDetails() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = getOnSurface()
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(5.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.ic_candy),
                contentDescription = "Home Details Icon",
                modifier = Modifier
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Other Details",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Text(
            text = "On the kitchen table is a jar with candy. You are explicitly invited to take as many as you want to. They are for guests and you are a guest. Please enjoy them.",
            modifier = Modifier
                .padding(10.dp, 0.dp),
            fontSize = 16.sp,
        )
        Text(
            text = "If the jar is empty, on the freezer are two more bags of candy. Please refill the jar if you take the last of the candy.",
            modifier = Modifier
                .padding(10.dp, 5.dp),
            fontSize = 16.sp,
        )
        Text(
            text = "Other than that, thank you so much for your help! Without you, I wouldn't be able to go see my family.",
            modifier = Modifier
                .padding(10.dp, 0.dp),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
fun FlipCard(maxScroll: MutableIntState, scrollState: ScrollState) {
    val coroutineScope = rememberCoroutineScope()
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500, easing = FastOutSlowInEasing)
    )
    val backOpacity by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500, easing = FastOutSlowInEasing)
    )

    Box(
        Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(getOnSurface()),
        contentAlignment = Alignment.Center
    ) {
        Card(
            Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12 * density
                }
                .clickable {
                    rotated = !rotated
                }
                .animateContentSize(tween(800)),
            colors = CardDefaults.cardColors(
                containerColor = getOnSurface()
            )
        ) {
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val maxS = remember { mutableStateOf("") }
                val currS = remember { mutableStateOf("") }
                val maxS2 = remember { mutableStateOf("") }
                val currS2 = remember { mutableStateOf("") }
                if (rotated) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                        Image(
                            modifier = Modifier
                                .graphicsLayer {
                                    rotationY = rotation
                                    cameraDistance = 8f * density
                                }
                                .alpha(backOpacity),
                            painter = painterResource(id = R.drawable.img_koda),
                            contentDescription = "Koda the dog",
                        )
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            getOnSurface()
                                        )
                                    )
                                )
                                .graphicsLayer {
                                    rotationY = rotation
                                    cameraDistance = 8f * density
                                }
                                .alpha(backOpacity),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Column {
                                Text(text = maxS.value)
                                Text(text = currS.value)
                                Text(text = maxS2.value)
                                Text(text = currS2.value)
                                Text(
                                    text = "KODA",
                                    modifier = Modifier
                                        .padding(15.dp, 0.dp, 0.dp, 3.dp)
                                        .fillMaxWidth(),
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight(600),
                                    letterSpacing = 2.sp,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = "My family's dog",
                                    modifier = Modifier
                                        .padding(15.dp, 0.dp, 0.dp, 20.dp)
                                        .fillMaxWidth(),
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                    LaunchedEffect(Unit) {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(maxScroll.intValue.plus(61))
                        }
                    }
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_gift),
                            contentDescription = "Gift Icon",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Tap for a small surprise",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                    }
                }

            }
        }
    }
}

/*@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}*/
