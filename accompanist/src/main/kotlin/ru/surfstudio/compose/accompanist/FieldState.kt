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
 
package ru.surfstudio.compose.accompanist

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