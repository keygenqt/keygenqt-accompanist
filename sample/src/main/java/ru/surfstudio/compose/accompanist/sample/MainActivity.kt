/*
 * Copyright 2021 Surf LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.surfstudio.compose.accompanist.sample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.surfstudio.compose.accompanist.BaseBottomSheetScaffold
import ru.surfstudio.compose.accompanist.keyboard.BaseViewKeyboard
import ru.surfstudio.compose.accompanist.sample.theme.TestTheme

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun MainScreen() {
    var isShowBottomSheetScaffold by remember { mutableStateOf(false) }
    val onClose = { isShowBottomSheetScaffold = false }

    TestTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = { isShowBottomSheetScaffold = true }
                ) {
                    Text(text = "Show dialog")
                }
                BaseViewKeyboard(forgotCodeText = "Forgot code?")
                BaseBottomSheetScaffold(
                    isShow = isShowBottomSheetScaffold,
                    onClose = onClose,
                    titleContent = {
                        DialogTitle(
                            hasCloseIcon = true,
                            title = "Title",
                            onClose = onClose
                        )
                    }
                ) {
                    Text("Text")
                }
            }
        }
    }
}

@Composable
fun DialogTitle(hasCloseIcon: Boolean, title: String, onClose: () -> Unit = {}) {
    if (hasCloseIcon) {
        Box(
            modifier = Modifier
                .padding(end = 6.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title
            )
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    onClose.invoke()
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
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title
            )
            Spacer(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview("Light", device = Devices.PIXEL_2_XL)
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_2_XL)
@ExperimentalComposeUiApi
@Composable
fun MainScreenPreview() {
    MainScreen()
}