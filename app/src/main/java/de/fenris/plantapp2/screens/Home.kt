package de.fenris.plantapp2.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HomeRepairService
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.ui.theme.getOnSurface

@Composable
fun HomeScreen() {
    Box(Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
        Utils.GetBackgroundImage()
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            GetKeyHandover()
            Spacer(modifier = Modifier.height(20.dp))
            GetInfo()
            Spacer(modifier = Modifier.height(20.dp))
            GetMoreLessInfo()
        }
    }
}

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
                .background(Color.Transparent),
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
        /*Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Where?",
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .width(65.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Placeholder",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "HS Harz Mensa",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }
        }*/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
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
        /*Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.contact),
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .width(65.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Placeholder",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 19.sp,
                    fontWeight = FontWeight(700),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
                    letterSpacing = 1.sp
                )
                Text(
                    text = "0000-00000000",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }
        }*/
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
            verticalAlignment = Alignment.CenterVertically
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
        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
fun GetMoreInfo() {
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
                imageVector = Icons.Default.Info,
                contentDescription = "Much More Info",
                modifier = Modifier
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "More Info",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Text(
            text =
            "I don't trust technology so you'll find the instructions on the kitchen table on paper " +
                    "as well. But since printing images is expensive and black and white " +
                    "images are not very helpful, I put the images with the plants name on my " +
                    "digital picture frame. It's on the kitchen table too.",
            modifier = Modifier
                .padding(10.dp, 3.dp),
            fontSize = 16.sp,
        )
        Text(
            text =
            "If you want to see the images, the instructions to the frame are there too. " +
                    "The picture frame turns on and off automatically at a time only it and " +
                    "the gods know, so don't worry about that.",
            modifier = Modifier
                .padding(10.dp, 3.dp),
            fontSize = 16.sp,
        )
        Text(
            text =
            "The spray bottle? On the kitchen table. A measuring cup? On the kitchen table. " +
                    "The watering can? NOT on the kitchen table. It's under it. My kitchen table " +
                    "is only a square meter in size and I'm not about to place 5l of water right " +
                    "next to the only written instructions.",
            modifier = Modifier
                .padding(10.dp, 3.dp),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(7.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}
