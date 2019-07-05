package com.samuelchowi.bellatech.secondary

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.samuelchowi.bellatech.common.ShakeUtil

class ShakeActionTracker(private val context: AppCompatActivity?) : LifecycleObserver {

    private var shakeUtil: ShakeUtil? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        shakeUtil = ShakeUtil {
            context?.run {
                Toast.makeText(context, "I'm watching you", Toast.LENGTH_LONG).show()
            }
        }
    }

    @Suppress("DEPRECATION")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        (context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager).run {
            shakeUtil?.let { registerListener(it, getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI) }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        (context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager).run {
            shakeUtil?.let { unregisterListener(it) }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        shakeUtil = null
    }
}