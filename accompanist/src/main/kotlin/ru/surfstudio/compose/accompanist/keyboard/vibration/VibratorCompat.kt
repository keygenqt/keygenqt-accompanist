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

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.content.ContextCompat

/**
 * Compat wrapper for [Vibrator]
 * @author Oleg Zhilo
 */
object VibratorCompat {

    /**
     * @param context [Context]
     * @return [Vibrator]
     */
    fun getVibrator(context: Context): Vibrator? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (ContextCompat.getSystemService(
                context,
                VibratorManager::class.java
            ) as VibratorManager).defaultVibrator
        } else {
            ContextCompat.getSystemService(context, Vibrator::class.java)
        }
    }

    /**
     * @param vibrator [Vibrator]
     * @param duration vibration duration
     */
    fun vibrate(vibrator: Vibrator, duration: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            vibrator.vibrate(duration)
        }
    }

    /**
     * @param vibrator [Vibrator]
     * @param timings vibration pattern
     * @param repeat  the index into pattern at which to repeat, or -1 if you don't want to repeat.
     */
    fun vibrate(vibrator: Vibrator, timings: LongArray, repeat: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createWaveform(timings, repeat)
            )
        } else {
            vibrator.vibrate(timings, repeat)
        }
    }
}
