package com.samuelchowi.bellatech.third

import android.content.Context
import android.hardware.*
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuelchowi.bellatech.R
import kotlinx.android.synthetic.main.activity_third.*


class ThirdActivity : AppCompatActivity(), SensorEventListener, LocationListener {

    private lateinit var sensorManager: SensorManager

    private var geomagneticField: GeomagneticField? = null

    private val rotations = FloatArray(9)
    private val orientations = FloatArray(3)

    private val accelerometer = FloatArray(3)
    private val magnetometer = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Empty
    }

    override fun onResume() {
        super.onResume()

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI)
        }

        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
            sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometer, 0, accelerometer.size)
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetometer, 0, magnetometer.size)
        }

        updateOrientationAngles()
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER || event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD)
            ctvCompass.invalidate()
    }

    private fun updateOrientationAngles() {

        SensorManager.getRotationMatrix(rotations, null, accelerometer, magnetometer)
        SensorManager.getOrientation(rotations, orientations)

        val grades = ((Math.toDegrees(orientations[0].toDouble()) + (geomagneticField?.declination ?: 0f))).toInt()
        tvwGrades.text = "$grades º"
        ctvCompass.orientation = grades
    }

    override fun onLocationChanged(currentLocation: Location?) {
        currentLocation?.let {
            geomagneticField = GeomagneticField(it.latitude.toFloat(), it.longitude.toFloat(), it.altitude.toFloat(), System.currentTimeMillis())
        }
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        // Empty
    }

    override fun onProviderEnabled(p0: String?) {
        // Empty
    }

    override fun onProviderDisabled(p0: String?) {
        // Empty
    }
}