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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            //Spacer(modifier = Modifier.height(20.dp))
            //GetMoreInfo()
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
                contentDescription = "Key",
                modifier = Modifier
                    .size(40.dp)
                    .scale(scaleX = -1f, scaleY = 1f)
                    .rotate(45.0f),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Handing over the Key",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "When?",
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
                    text = "01.08. at 12:00",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }
        }
        Row (
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
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "From?",
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
                    text = "Person for Week 1",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Contact:",
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
                contentDescription = "Person",
                modifier = Modifier
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "My Trip",
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
                    text = "Return Date:",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
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
                    text = "13.08. (probably late)",
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }
        }
        Text(
            text =
            "I might not have reliable internet access, so I might not be able to respond to " +
                    "messages quickly. Some plants are drama queens so if you " +
                    "think a plant is dying, check the notes first.",
            modifier = Modifier
                .padding(10.dp, 5.dp),
            fontSize = 16.sp,
        )
        Text(
            text =
            "Notable drama queens are the Peace Lilies and the Bonsai. If they are running low on " +
                    "water, they will let their leaves hang like they've never seen a drop of water " +
                    "in their whole life. They have. The plants are manipulating you. Don't fall for " +
                    "it.",
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
                contentDescription = "Tools & Co.",
                modifier = Modifier
                    .size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Tools & Co.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row {
            Text(
                text = "Watering Can:",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Column {
                Text(
                    text =
                    "Under the kitchen table",
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 16.sp,
                )
                Text(
                    text =
                    "It's a 5l can, so it's heavy when full. It fits under the " +
                            "kitchen tap if you place it with the spout facing the top right corner.",
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Spray Bottle:",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text =
                "On the kitchen table",
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Measuring Cup:",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text =
                "On the kitchen table",
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Instructions:",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text =
                "On the kitchen table",
                modifier = Modifier
                    .padding(10.dp, 3.dp),
                fontSize = 16.sp,
            )
        }
        Row {
            Text(
                text = "Frame:",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Column {
                Text(
                    text =
                    "On the kitchen table",
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 16.sp,
                )
                Text(
                    text =
                    "It turns on and off by itself, so don't worry if it's on.",
                    modifier = Modifier
                        .padding(10.dp, 3.dp),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Frame Remote:",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .width(125.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
            Text(
                text =
                "On the kitchen table",
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
