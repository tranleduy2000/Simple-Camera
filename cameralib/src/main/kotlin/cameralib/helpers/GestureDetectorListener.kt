package cameralib.helpers

import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent

open class GestureDetectorListener : SimpleOnGestureListener() {
    override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        return super.onFling(e1, e2, velocityX, velocityY)
    }
}
