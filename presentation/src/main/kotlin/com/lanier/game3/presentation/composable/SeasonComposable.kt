package com.lanier.game3.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lanier.game3.domain.model.Season
import com.lanier.game3.manager.presentation.R

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/26 02:04
 */
@Composable
fun SeasonSelection(
    selectedSeason: Season,
    onSeasonSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val seasons = listOf(
        0 to stringResource(R.string.season_none),
        1 to stringResource(R.string.season_spring),
        2 to stringResource(R.string.season_summer),
        3 to stringResource(R.string.season_autumn),
        4 to stringResource(R.string.season_winter)
    )

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(4.dp)
                ),
        ) {
            seasons.forEach { (value, seasonName) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (value == selectedSeason.value),
                        onClick = { onSeasonSelected(value) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = seasonName)
                }
            }
        }

        Text(
            text = stringResource(R.string.season),
            modifier = Modifier
                .padding(start = 12.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 4.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}