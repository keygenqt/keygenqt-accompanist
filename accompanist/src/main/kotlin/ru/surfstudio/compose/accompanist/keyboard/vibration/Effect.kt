/**
 * Copyright © 2021 Surf. All rights reserved.
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
