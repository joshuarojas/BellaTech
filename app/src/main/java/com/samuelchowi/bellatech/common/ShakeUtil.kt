package com.samuelchowi.bellatech.common

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager


class ShakeUtil(private val listener: () -> Unit) : SensorEventListener {

    private var mShakeTimestamp: Long = 0

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Empty method
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.run {
            val x = values[0]
            val y = values[1]
            val z = values[2]

            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            val gForce = Math.sqrt((gX * gX + gY * gY + gZ * gZ).toDouble()).toFloat()

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                val now = System.currentTimeMillis()
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return
                }

                mShakeTimestamp = now
                listener()
            }
        }
    }

    companion object {
        private const val SHAKE_THRESHOLD_GRAVITY = 2.7f
        private const val SHAKE_SLOP_TIME_MS = 1000
    }
}