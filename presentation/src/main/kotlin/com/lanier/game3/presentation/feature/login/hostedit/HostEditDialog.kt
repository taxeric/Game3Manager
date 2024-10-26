package com.lanier.game3.presentation.feature.login.hostedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lanier.game3.manager.presentation.R
import com.lanier.game3.presentation.feature.login.HostEditDialogUiState

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 22:36
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostEditDialog(
    state: HostEditDialogUiState,
    defValue: () -> String,
    onDismissRequest: (Boolean?) -> Unit,
    onChanged: (String) -> Unit,
) {
    if (state is HostEditDialogUiState.Hide) {
        return
    }
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest(null) }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp),
        ) {
            Text(
                text = stringResource(R.string.input_new_api_address_title),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .weight(
                            weight = 1f,
                        ),
                    singleLine = true,
                    value = defValue(),
                    onValueChange = onChanged,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "lead icon",
                        )
                    },
                    label = {
                        Text(text = stringResource(R.string.new_api_address))
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        onDismissRequest.invoke(true)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "done",
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}