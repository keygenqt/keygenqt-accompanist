package com.keygenqt.accompanist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

class FieldState(text: String = "") {

    private var _default = TextFieldValue(text = text)
    private var _text: TextFieldValue by mutableStateOf(_default)

    var text: TextFieldValue
        get() = _text
        set(value) {
            _text = value
        }

    fun getValue(): String {
        return _text.text
    }

    fun positionToEnd() {
        _text = _text.copy(
            selection = TextRange(_text.text.length, _text.text.length)
        )
    }

    fun default(value: String): FieldState {
        _default = TextFieldValue(text = value)
        _text = _default
        return this
    }

    fun clear(): FieldState {
        _text = _default
        return this
    }
}