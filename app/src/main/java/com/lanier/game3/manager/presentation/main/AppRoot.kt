package com.lanier.game3.manager.presentation.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.FrameMetricsAggregator.ANIMATION_DURATION
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanier.game3.manager.presentation.feature.NavGraphs
import com.lanier.game3.manager.presentation.feature.destinations.LoginPageDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/4 19:39
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRoot(
    viewmodel: MainViewModel = hiltViewModel()
) {
    val engine = rememberAnimatedNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = {
                val fromRoute = initialState.destination.route ?: ""
                val toRoute = targetState.destination.route ?: ""
                    if (fromRoute == toRoute) {
                        fadeIn()
                    } else {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(
                                durationMillis = ANIMATION_DURATION,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }
            },
            exitTransition = {
                val fromRoute = initialState.destination.route ?: ""
                val toRoute = targetState.destination.route ?: ""
                    if (fromRoute == toRoute) {
                        fadeOut()
                    } else {
                        slideOutHorizontally(
                            targetOffsetX = { -it / 5 },
                            animationSpec = tween(
                                durationMillis = ANIMATION_DURATION,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        )
    )

    val startRoute = if (viewmodel.isLoggedIn) {
        NavGraphs.root.startRoute
    } else {
        LoginPageDestination
    }

    Scaffold(
        content = { paddingValues ->
            DestinationsNavHost(
                modifier = Modifier.padding(paddingValues),
                navGraph = NavGraphs.root,
                startRoute = startRoute,
                engine = engine
            )
        }
    )
}
