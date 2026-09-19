package com.codehijackers.guldnet.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehijackers.guldnet.repository.AppLanguage
import com.codehijackers.guldnet.repository.LanguageRepository

private data class SettingsStrings(
    val title: String,
    val languageSection: String,
    val languageDescription: String,
    val englishLabel: String,
    val zuluLabel: String,
    val backDescription: String
)

private val englishStrings = SettingsStrings(
    title = "Settings",
    languageSection = "LANGUAGE",
    languageDescription = "Choose the language used across your GuildNet settings.",
    englishLabel = "English",
    zuluLabel = "isiZulu",
    backDescription = "Back"
)

private val zuluStrings = SettingsStrings(
    title = "Izilungiselelo",
    languageSection = "ULIMI",
    languageDescription = "Khetha ulimi olusetshenziswa kuzilungiselelo zakho ze-GuildNet.",
    englishLabel = "isiNgisi",
    zuluLabel = "isiZulu",
    backDescription = "Emuva"
)

@Composable
fun SettingsScreen(
    onBackClicked: () -> Unit = {}
) {
    val currentLanguage by LanguageRepository.language.collectAsState()
    val strings = if (currentLanguage == AppLanguage.ZULU) zuluStrings else englishStrings

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = strings.backDescription
                )
            }
            Text(
                text = strings.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = strings.languageSection,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = strings.languageDescription,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                LanguageOptionRow(
                    label = strings.englishLabel,
                    selected = currentLanguage == AppLanguage.ENGLISH,
                    onClick = { LanguageRepository.setLanguage(AppLanguage.ENGLISH) }
                )
                LanguageOptionRow(
                    label = strings.zuluLabel,
                    selected = currentLanguage == AppLanguage.ZULU,
                    onClick = { LanguageRepository.setLanguage(AppLanguage.ZULU) }
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun LanguageOptionRow(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
