package ru.surfstudio.compose.accompanist.sample.otp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.surfstudio.compose.accompanist.keyboard.BaseViewKeyboard
import ru.surfstudio.compose.accompanist.sample.theme.TestTheme
import ru.surfstudio.compose.forms.base.FormFieldState
import ru.surfstudio.compose.forms.other.DotsNumbers

@Composable
fun OtpScreen(viewModel: OtpViewModel) {
    val codeState = remember { FormFieldState() }
    val isSucceed: Boolean by viewModel.isSucceed.collectAsState()
    val error: String? by viewModel.error.collectAsState()
    val isBlank = codeState.getValue().isBlank()

    LaunchedEffect(isSucceed) {
        if (isSucceed) {
            delay(500)
            codeState.clear()
        }
    }

    TestTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DotsNumbers(
                    modifier = Modifier.padding(top = 56.dp),
                    isFocusable = false,
                    state = codeState,
                    isSuccess = isSucceed,
                    error = error,
                    count = PIN_CODE_LENGTH
                )
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = 24.dp)
                ) {
                    BaseViewKeyboard(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        forgotCodeText = "Forgot code?",
                        isShowRemove = !isBlank,
                        isShowFingerprint = isBlank,
                        onPress = { key ->
                            codeState.addValue(key)
                            viewModel.validate(codeState.getValue())
                        },
                        onRemove = {
                            codeState.removeLast()
                        }
                    )
                }
            }
        }
    }
}