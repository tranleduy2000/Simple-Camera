package cameralib.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Handler
import android.util.TypedValue
import android.view.ViewGroup
import cameralib.extensions.getProperPrimaryColor

class FocusCircleView(context: Context) : ViewGroup(context) {
    private val circleRadius: Float
    private val animationDuration = 500L

    private var mDrawCircle = false
    private var mHandler: Handler
    private var mPaint: Paint
    private var mLastCenterX = 0f
    private var mLastCenterY = 0f

    init {
        val r = context.resources
        circleRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30.toFloat(), r.displayMetrics)
        val circleStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3.toFloat(), r.displayMetrics)

        setWillNotDraw(false)
        mHandler = Handler()
        mPaint = Paint().apply {
            style = Paint.Style.STROKE
            color = context.getProperPrimaryColor()
            strokeWidth = circleStrokeWidth
        }
    }

    fun setStrokeColor(color: Int) {
        mPaint.color = color
    }

    fun drawFocusCircle(x: Float, y: Float) {
        mLastCenterX = x
        mLastCenterY = y
        toggleCircle(true)

        mHandler.removeCallbacksAndMessages(null)
        mHandler.postDelayed({
            toggleCircle(false)
        }, animationDuration)
    }

    private fun toggleCircle(show: Boolean) {
        mDrawCircle = show
        invalidate()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mDrawCircle) {
            canvas.drawCircle(mLastCenterX, mLastCenterY, circleRadius, mPaint)
        }
    }
}
