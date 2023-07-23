package de.fenris.plantapp2.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.os.LocaleListCompat
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.data.Language
import de.fenris.plantapp2.organisation.UserStore
import de.fenris.plantapp2.navigation.getWeight
import de.fenris.plantapp2.ui.theme.getNavColor
import de.fenris.plantapp2.ui.theme.getOnSurface
import de.fenris.plantapp2.ui.theme.getOnSurfaceVariant
import de.fenris.plantapp2.ui.theme.getSurfaceHighest
import de.fenris.plantapp2.ui.theme.isAppInDarkTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

var languages = emptyList<Language>()

fun getLanguages(context: Context): List<Language> {
    val system = Language(
        context.getString(R.string.follow_system_language),
        "Follow System",
        "system",
        "system"
    )
    val german = Language("Deutsch (Deutschland)", "German (Germany)", "de", "de-rDE")
    val englishGB =
        Language("English (United Kingdom)", "English (United Kingdom)", "en-GB", "en-rGB")
    val englishUS =
        Language("English (United States)", "English (United States)", "en-US", "en-rUS")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(system, german, englishGB, englishUS)
    } else {
        listOf(german, englishGB, englishUS)
    }
}

var chosenLanguage: String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) "system" else "en-GB"
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

    val darkModePref = store.getDarkModePreference.collectAsState(isSystemInDarkTheme())
    val themeSetByUser = store.getDarkModePreference.collectAsState(initial = false)

    languagePref = store.getLanguagePreference.collectAsState(initial = chosenLanguage)

    languages = getLanguages(context)
    var showLanguageChoice by remember {
        mutableStateOf(false)
    }
    var darkModeChoice by remember {
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
                    stringResource(R.string.language_setting),
                    modifier = Modifier.padding(0.dp, 0.dp),
                    fontSize = 18.sp
                )
            },
            supportingText = {
                Text(
                    if (languagePref.value == "system" || languagePref.value == "") {
                        stringResource(id = R.string.follow_system_language)
                    } else {
                        languages.first { it.languageTag == languagePref.value }.language
                    },
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
                    color = if (isAppInDarkTheme()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
                )
            },
            trailingContent = {
                Switch(
                    checked = darkModePref.value,
                    onCheckedChange = {
                        darkModeChoice = it
                        CoroutineScope(Dispatchers.IO).launch {
                            if (!themeSetByUser.value) store.saveThemeSetByUser(true)
                            store.saveDarkModePreference(darkModeChoice)
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
                                text = stringResource(R.string.language_title),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = getWeight()
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    showLanguageChoice = false
                                }) {
                                Icon(
                                    Icons.Default.Close,
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
                        if (languagePref.value == language.languageTag || (languagePref.value == "" && language.languageTag == "system")) {
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