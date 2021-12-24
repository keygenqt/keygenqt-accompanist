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
package ru.surfstudio.compose.accompanist.keyboard.vibration

import android.os.Vibrator

/**
 * Vibration effects
 * @author Oleg Zhilo
 */
sealed class Effect {

    abstract fun run(vibrator: Vibrator)

    class Click(private val duration: Long = 60) : Effect() {

        override fun run(vibrator: Vibrator) {
            VibratorCompat.vibrate(vibrator, duration)
        }
    }

    class Error(
        private val timings: LongArray = arrayOf(0L, 70L, 0L, 70L).toLongArray()
    ) : Effect() {

        override fun run(vibrator: Vibrator) {
            VibratorCompat.vibrate(vibrator, timings, -1)
        }
    }
}
