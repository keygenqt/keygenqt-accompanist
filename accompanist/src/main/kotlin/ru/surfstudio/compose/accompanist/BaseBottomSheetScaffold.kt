/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package ru.surfstudio.compose.accompanist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import ru.surfstudio.compose.accompanist.BottomSheetScaffoldActivity.Companion.DEFAULT_BACKGROUND_COLOR

/**
 * State for driving open/close
 */
private val state: MutableStateFlow<Boolean> = MutableStateFlow(true)

/**
 * Bottom sheet scaffold for app with auto show/hide and resize
 *
 * @author Vitaliy Zarubin
 */
@Composable
fun BaseBottomSheetScaffold(
    isShow: Boolean,
    title: String? = null,
    isIconClose: Boolean = true,
    navigationBarColor: Color = Color.White,
    systemBarsColor: Color = Color.White,
    backgroundColor: Color = DEFAULT_BACKGROUND_COLOR,
    onClose: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {

    val context = LocalContext.current
    val stateAction = state.collectAsState()

    LaunchedEffect(isShow) {
        state.value = isShow
        if (isShow) {
            BottomSheetScaffoldActivity.setActivityData(
                title = title,
                isIconClose = isIconClose,
                systemBarsColor = systemBarsColor,
                composeContent = content,
                navigationBarColor = navigationBarColor,
                backgroundColor = backgroundColor
            )
            context.startActivity(Intent(context, BottomSheetScaffoldActivity::class.java))
        }
    }

    LaunchedEffect(stateAction.value) {
        if (!stateAction.value && isShow) {
            onClose.invoke()
        }
    }
}

/**
 * Bottom sheet scaffold run in activity for exclude dependence compose stack
 *
 * @author Vitaliy Zarubin
 */
@OptIn(ExperimentalMaterialApi::class)
class BottomSheetScaffoldActivity : FragmentActivity() {
    companion object {

        val DEFAULT_BACKGROUND_COLOR = Color.Black.copy(alpha = 0.7f)

        private var activityTitle: String? = null
        private var activityIsIconClose: Boolean = true
        private var activitySystemBarsColor: Color? = null
        private var activityNavigationBarColor: Color? = null
        private var activityBackgroundColor: Color = DEFAULT_BACKGROUND_COLOR
        private var activityComposeContent: (@Composable ColumnScope.() -> Unit)? = null
        private var currentActivity: FragmentActivity? = null

        fun onBackPressed() {
            currentActivity?.onBackPressed()
        }

        fun setActivityData(
            title: String?,
            isIconClose: Boolean = true,
            systemBarsColor: Color?,
            navigationBarColor: Color?,
            backgroundColor: Color,
            composeContent: (@Composable ColumnScope.() -> Unit),
        ) {
            activityTitle = title
            activityIsIconClose = isIconClose
            activitySystemBarsColor = systemBarsColor
            activityNavigationBarColor = navigationBarColor
            activityBackgroundColor = backgroundColor
            activityComposeContent = composeContent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentActivity = this
        WindowCompat.setDecorFitsSystemWindows(window, false)
        theme.applyStyle(R.style.Theme_Transparent, true)
        setContent {
            MaterialTheme {
                ProvideWindowInsets {
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        activitySystemBarsColor?.let {
                            systemUiController.setSystemBarsColor(it, true)
                        }
                        activityNavigationBarColor?.let {
                            systemUiController.setNavigationBarColor(it, true)
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsWithImePadding()
                            .background(activityBackgroundColor)
                    ) {
                        BaseBottomSheetScaffold()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        state.value = false
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            overridePendingTransition(0, 0)
        }, 150)
    }

    @Composable
    fun BaseBottomSheetScaffold() {

        val stateAction = state.collectAsState()

        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberBottomSheetState(
                initialValue = BottomSheetValue.Collapsed
            )
        )

        BottomSheetScaffold(
            modifier = Modifier,
            sheetGesturesEnabled = false,
            sheetBackgroundColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetElevation = 0.dp,
            sheetContent = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                            .background(MaterialTheme.colors.background)
                            .align(Alignment.BottomStart)
                    ) {

                        if (activityIsIconClose) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 6.dp)
                                    .fillMaxWidth()
                            ) {
                                activityTitle?.let {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = it
                                    )
                                }
                                IconButton(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    onClick = {
                                        state.value = false
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .padding(12.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Image(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .padding(end = 6.dp)
                                    .fillMaxWidth()
                            ) {
                                activityTitle?.let {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = it
                                    )
                                }
                                Spacer(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .align(Alignment.CenterEnd)
                                )
                            }
                        }
                        Column(
                            // modifier = Modifier.spacePageHorizontal()
                        ) {
                            activityComposeContent?.invoke(this)
                        }
                        // Spacer(modifier = Modifier.padding(bottom = SpaceSize.spacePageVertical))
                    }
                }
            }
        ) {}

        LaunchedEffect(stateAction.value) {
            if (stateAction.value) {
                bottomSheetScaffoldState.bottomSheetState.expand()
            } else {
                onBackPressed()
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }
    }
}