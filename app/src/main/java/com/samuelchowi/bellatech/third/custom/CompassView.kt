package com.samuelchowi.bellatech.third.custom

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.scale
import com.samuelchowi.bellatech.R
import java.util.concurrent.atomic.AtomicBoolean


class CompassView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val drawing = AtomicBoolean(false)
    private val paint = Paint(ANTI_ALIAS_FLAG)

    private var parentWidth = 0
    private var parentHeight = 0
    private var _matrix: Matrix? = null
    private var bitmap: Bitmap? = null

    var orientation: Int = 0

    init {
        _matrix = Matrix()
        val dim = resources.getDimensionPixelSize(R.dimen.main_250dp)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_compass_demo).scale(dim, dim, false)
    }

    /**
     * {@inheritDoc}
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(parentWidth, parentHeight)
    }

    /**
     * {@inheritDoc}
     */
    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) throw NullPointerException()

        if (!drawing.compareAndSet(false, true)) return

        val bearing = orientation

        val bitmapWidth = bitmap!!.width
        val bitmapHeight = bitmap!!.height

        val canvasWidth = canvas.width
        val canvasHeight = canvas.height
        if (bitmap!!.width > canvasWidth || bitmap!!.height > canvasHeight) {
            // Resize the bitmap to the size of the canvas
            bitmap = Bitmap.createScaledBitmap(bitmap!!, (bitmapWidth * .9).toInt(), (bitmapHeight * .9).toInt(), true)
        }

        val bitmapX = bitmap!!.width / 2F
        val bitmapY = bitmap!!.height / 2F

        val parentX = parentWidth / 2
        val parentY = parentHeight / 2

        val centerX = parentX - bitmapX
        val centerY = parentY - bitmapY

        val rotation = 360 - bearing

        _matrix!!.reset()
        // Rotate the bitmap around it's center point so it's always pointing
        // north
        _matrix!!.setRotate(rotation.toFloat(), bitmapX, bitmapY)
        // Move the bitmap to the center of the canvas
        _matrix!!.postTranslate(centerX, centerY)

        canvas.drawBitmap(bitmap!!, _matrix!!, paint)

        drawing.set(false)
    }
}