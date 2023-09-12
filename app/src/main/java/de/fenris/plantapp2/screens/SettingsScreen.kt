package de.fenris.plantapp2.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.os.LocaleListCompat
import de.fenris.plantapp2.BuildConfig
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.data.Language
import de.fenris.plantapp2.navigation.getWeight
import de.fenris.plantapp2.organisation.UserStore
import de.fenris.plantapp2.ui.theme.getNavColor
import de.fenris.plantapp2.ui.theme.getOnSurface
import de.fenris.plantapp2.ui.theme.getOnSurfaceVariant
import de.fenris.plantapp2.ui.theme.getSurfaceHighest
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Field
import java.util.*

var languages = emptyList<Language>()

fun getLanguageList(): List<Language> {
    val german = Language("Deutsch (Deutschland)", "German (Germany)", "de", "de-rDE")
    val englishGB =
        Language("English (United Kingdom)", "English (United Kingdom)", "en-GB", "en-rGB")
    val englishUS =
        Language("English (United States)", "English (United States)", "en-US", "en-rUS")
    return listOf(german, englishGB, englishUS)
}

var chosenLanguage: String =
    if (LocaleListCompat.getDefault()[0]?.language == "de") "de" else "en-GB"
var languagePref: State<String> = mutableStateOf(chosenLanguage)

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val store = UserStore(context)
    val darkModePref = store.getUserChoiceTheme.collectAsState(initial = isAppInDarkTheme2())
    val themeSetByUser = store.getThemeSetByUser.collectAsState(initial = false)

    languagePref = store.getLanguagePreference.collectAsState(initial = chosenLanguage)

    languages = getLanguageList()
    var showLanguageChoice by remember {
        mutableStateOf(false)
    }
    var darkModeChoice by remember {
        mutableStateOf(false)
    }
    var showAbout by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                getOnSurface()
            )
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        ListItem(
            headlineText = {
                Text(
                    stringResource(R.string.language),
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    languages.first { it.languageTag == chosenLanguage }.language,
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
                .clickable {
                    showLanguageChoice = true
                }
        )
        Divider(
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        ListItem(
            headlineText = {
                Text(
                    "Dark Mode",
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    if (isAppInDarkTheme()) {
                        stringResource(R.string.switch_on)
                    } else {
                        stringResource(R.string.switch_off)
                    },
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = if (isAppInDarkTheme()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.6f
                    )
                )
            },
            trailingContent = {
                Switch(
                    checked = if (darkModePref.value != null) darkModePref.value!! else isSystemInDarkTheme(),
                    onCheckedChange = {
                        darkModeChoice = it
                        CoroutineScope(Dispatchers.IO).launch {
                            if (!themeSetByUser.value) store.saveThemeSetByUser(true)
                            store.saveDarkModePreference(darkModeChoice)
                            store.saveUserChoiceTheme(darkModeChoice)
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                        checkedTrackColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                        uncheckedTrackColor = getSurfaceHighest(),
                        uncheckedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
        )
        Divider(
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        ListItem(
            headlineText = {
                Text(
                    stringResource(R.string.about),
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    stringResource(R.string.about_details),
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
                .clickable {
                    showAbout = true
                }
        )
    }

    if (showLanguageChoice) {
        Dialog(
            onDismissRequest = { showLanguageChoice = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false
            ),
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(R.string.language),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = getWeight()
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    showLanguageChoice = false
                                }) {
                                Icon(
                                    Icons.Default.ArrowBackIos,
                                    contentDescription = "Close Dialog"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = getNavColor(),
                            titleContentColor = getOnSurfaceVariant()
                        ),
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Box(Modifier.padding(it)) {
                    LanguageSelection(store)
                }
            }
        }
    }

    if (showAbout) {
        Dialog(
            onDismissRequest = { showAbout = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false
            ),
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(R.string.about),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = getWeight()
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    showAbout = false
                                }) {
                                Icon(
                                    Icons.Default.ArrowBackIos,
                                    contentDescription = "Close Dialog"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = getNavColor(),
                            titleContentColor = getOnSurfaceVariant()
                        ),
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Box(Modifier.padding(it)) {
                    AboutScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    var showAppDetails by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                getOnSurface()
            )
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        val builder = StringBuilder()
        var error = false

        val fields: Array<Field> = VERSION_CODES::class.java.fields
        for (field in fields) {
            val fieldName: String = field.name
            var fieldValue = -1
            try {
                fieldValue = field.getInt(Any())
            } catch (e: Exception) {
                e.printStackTrace()
                error = true
            }
            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(fieldName)
            }
        }
        if (error) {
            builder.clear().append(stringResource(R.string.android_version_name_error))
        }
        ListItem(
            headlineText = {
                Text(
                    "Android Version",
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    Build.VERSION.RELEASE,
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
        )
        Divider(
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        ListItem(
            headlineText = {
                Text(
                    stringResource(R.string.android_version_name),
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    builder.toString(),
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = if (!error) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.error
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
        )
        Divider(
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        ListItem(
            headlineText = {
                Text(
                    "SDK Version",
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    Build.VERSION.SDK_INT.toString(),
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
        )
        Divider(
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        ListItem(
            headlineText = {
                Text(
                    stringResource(R.string.phone_model),
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    Build.MODEL.uppercase(),
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
        )
        Divider(
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        ListItem(
            headlineText = {
                Text(
                    "App Details",
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    "App Version: ${BuildConfig.VERSION_NAME}",
                    modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 0.dp),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Utils
                    .getBackgroundColor()
                    .compositeOver(MaterialTheme.colorScheme.background)
            ),
            modifier = Modifier
                .padding(0.dp, 0.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showAppDetails = true
                    }
                )
        )
    }
    if (showAppDetails) {
        Dialog(
            onDismissRequest = { showAppDetails = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false
            ),
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(R.string.about_app),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = getWeight()
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    showAppDetails = false
                                }) {
                                Icon(
                                    Icons.Default.ArrowBackIos,
                                    contentDescription = "Close Dialog"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = getNavColor(),
                            titleContentColor = getOnSurfaceVariant()
                        ),
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Box(Modifier.padding(it)) {
                    AboutApp()
                }
            }
        }
    }
}


@Composable
fun AboutApp() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .padding(10.dp, 10.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight(500),
            letterSpacing = 0.1.em,
        )
        Text(
            text = "Version: ${BuildConfig.VERSION_NAME}",
            fontSize = 16.sp,
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 10.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "App Icon",
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .size(150.dp)
        )
        Text(
            text = "\u00A9 ${getYear()} Elisa Johanna Woelk",
            fontSize = 16.sp,
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 10.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

fun getYear(): String {
    val year = Calendar.getInstance().get(Calendar.YEAR)
    return if (year == 2023) {
        year.toString()
    } else "2023 - $year"
}

@Composable
fun LanguageSelection(store: UserStore) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    var selectedLanguage by remember { mutableStateOf(chosenLanguage) }

    val onClickRefreshActivity = {
        context.findActivity()?.runOnUiThread {
            val appLocale =
                LocaleListCompat.forLanguageTags(selectedLanguage)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }
    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                getOnSurface()
            )
    ) {
        itemsIndexed(items = languages) { index, language ->
            Card(
                modifier = Modifier
                    .padding(0.dp, 0.dp)
                    .selectable(
                        selected = languagePref.value == language.languageTag,
                        onClick = {
                            selectedLanguage = language.languageTag
                            chosenLanguage = language.languageTag

                            CoroutineScope(Dispatchers.IO).launch {
                                store.saveLanguagePreference(language.languageTag)
                            }
                            if (language.languageTag != "system") {
                                onClickRefreshActivity()
                            }

                        }),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Utils.getBackgroundColor()
                        .compositeOver(MaterialTheme.colorScheme.background)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp)
                ) {
                    Column {
                        Text(
                            language.language,
                            modifier = Modifier.padding(10.dp, 5.dp, 0.dp, 0.dp),
                            fontSize = 18.sp
                        )
                        Text(
                            language.languageEN,
                            modifier = Modifier.padding(10.dp, 3.dp, 0.dp, 5.dp),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
                        if (languagePref.value == language.languageTag || chosenLanguage == language.languageTag) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Language Selected",
                                modifier = Modifier
                                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
                                    .size(30.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
            if (index != languages.size - 1) {
                Divider(modifier = Modifier.padding(10.dp, 2.dp))
            }
        }
    }
}