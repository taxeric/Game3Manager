package com.lanier.game3.manager.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanier.game3.manager.R

@Composable
fun AppTopBar(
    title: String? = null,
    titleColor: Color = colorResource(id = R.color.color_222222),
    titleFontWeight: FontWeight = FontWeight.W500,
    titleFontSize: TextUnit = 14.sp,
    backgroundColor: Color = Color.Transparent,
    backgroundImagePainter: Painter = painterResource(id = R.drawable.ic_arrow_left_black),
    hideBackBtn: Boolean = false,
    backBtnPaddingValues: PaddingValues = PaddingValues(horizontal = 16F.dp),
    statusBarsPadding: @Composable () -> Dp = { WindowInsets.statusBars.asPaddingValues().calculateTopPadding() },
    onClickBack: () -> Unit,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(24.dp + statusBarsPadding())
            .background(backgroundColor),
    ) {
        if (!hideBackBtn) {
            Image(
                painter = backgroundImagePainter,
                contentDescription = "Back",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(backBtnPaddingValues)
                    .clickable { onClickBack.invoke() }
            )
        }
        if (!title.isNullOrBlank())
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .padding(horizontal = 64F.dp),
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = titleColor,
                    fontWeight = titleFontWeight,
                    fontSize = titleFontSize,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        content()
    }
}
