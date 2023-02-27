package com.kozak.pw.presentation.dashboard

import android.content.Context
import android.util.Log
import android.view.MotionEvent

class HexImageView(context: Context) : androidx.appcompat.widget.AppCompatImageView(context) {

    var touchEventX = 0f
    var touchEventY = 0f
    var offsetAfterMoveX = 0f
    var offsetAfterMoveY = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("HEXiv", "onTouchEvent.ACTION_DOWN")
                touchEventX = event.x
                touchEventY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("HEXiv", "onTouchEvent.ACTION_MOVE")
                val newOffsetX = touchEventX - event.x
                val newOffsetY = touchEventY - event.y

                offsetAfterMoveX += newOffsetX
                offsetAfterMoveY += newOffsetY
                touchEventX =  event.x
                touchEventY =  event.y
                Log.d("HEXiv", "afterMove:: x: $offsetAfterMoveX, y: $offsetAfterMoveY, " +
                        "touchEventX =  $touchEventX, touchEventY =  $touchEventY")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("HEX", "onTouchEvent.ACTION_UP")
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}