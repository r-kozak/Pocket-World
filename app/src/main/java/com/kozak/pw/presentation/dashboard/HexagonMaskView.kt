package com.kozak.pw.presentation.dashboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View.OnClickListener
import android.view.ViewGroup.LayoutParams
import android.widget.GridLayout
import android.widget.ImageView
import com.kozak.pw.R
import kotlin.math.sqrt


/**
 * @source https://stackoverflow.com/questions/8840729/android-hexagon-grid
 */
class HexagonMaskView : GridLayout {
    private val mImageIds = mutableListOf<Int>()

    private var hexagonPath: Path? = null
    private var hexagonBorderPath: Path? = null
    private var radius = 0f
    private var width = 0f
    private var height = 0f
    private var maskColor = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        hexagonPath = Path()
        hexagonBorderPath = Path()
        maskColor = -0xfe0089
    }

    fun setRadius(r: Float) {
        radius = r
        calculatePath()
    }

    fun setMaskColor(color: Int) {
        maskColor = color
        invalidate()
    }

    private fun calculatePath() {
        val triangleHeight = (Math.sqrt(3.0) * radius / 2).toFloat()
        val centerX = width / 2
        val centerY = height / 2
        hexagonPath!!.moveTo(0f + 10, 0f + 10)
        hexagonPath!!.lineTo(width - 10, 0f + 10)
        hexagonPath!!.lineTo(width - 10, height - 10)
        hexagonPath!!.lineTo(0f + 10, height - 10)
        val radiusBorder = radius - 5
        val triangleBorderHeight = (Math.sqrt(3.0) * radiusBorder / 2).toFloat()
        hexagonBorderPath!!.moveTo(0f, 0f)
        hexagonBorderPath!!.lineTo(width, 0f)
        hexagonBorderPath!!.lineTo(width, height)
        hexagonBorderPath!!.lineTo(0f, height)
        // hexagonBorderPath!!.moveTo(0f, height)
        invalidate()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val mSize = 45
        Log.d("HEX", "board.onlayout called with size $mSize l: $l r: $r t: $t b: $b")

        //If the dimensions of the board haven't changed, a redraw isn't necessary. Just update the images of the views instead by calling invalidate().
        if (!changed && !mSizeInvalidated) {
            invalidate()
            return
        }
        val childCount: Int = 0

        //Calculate some useful parameters.
        val radius = 40
        val verticalMargin = -radius.toFloat() / 3.8f
        val horizontalMargin = (sqrt(3.0).toFloat() / 2.07f - 1) * radius
        val height = 2 * radius
        val width = height
        val effectiveHeight = height + 2 * verticalMargin
        val effectiveWidth = width + 2 * horizontalMargin
        val totalHeight: Int = radius * (3 * mSize + 1) / 2
        val totalWidth: Float = (mSize.toFloat() * 3 - 1) / 2 * Math.sqrt(3.0).toFloat() * radius
        val layoutParams = LayoutParams(width.toInt(), height.toInt())

        //Code to calculate the offsets for horizontal and vertical centering (this is an option in the .xml file)
        //The GAME_TYPE_HEX creates a tilted rectangular board and GAME_TYPE_Y creates a triangular board.
        var x_offset_row: Float = 10f
        val mCenterHorizontal = 1
        when (mCenterHorizontal) {
            1 -> {
                x_offset_row += Math.max(0f, (r - l - totalWidth) / 2)
            }
            2 -> {
                x_offset_row += Math.max(0f, r - l - totalWidth)
            }
            0 -> {}
            else -> {}
        }

        //calculate the y_offset for vertical centering.
        var y_offset = 0f
        val mCenterVertical = 1
        val zero: Double = 0.0
        when (mCenterVertical) {
            1 -> {
                y_offset = Math.max(zero, ((b - t - totalHeight) / 2).toDouble()).toFloat()
            }
            2 -> {
                y_offset = Math.max(zero, (b - t - totalHeight).toDouble()).toFloat()
            }
        }
        var cell = 0
        for (row in 0 until mSize) {
            var x_offset = x_offset_row

            var rowLength: Int = mSize - 1
            if (row % 2 == 0) {
                rowLength = mSize
            }

            Log.d("HEX", "Drawing row $row with $rowLength cells.")
            for (col in 0 until rowLength) {
                var v: ImageView
                if (cell < childCount) {
                    v = getChildAt(cell) as ImageView
                } else {
                    v = ImageView(super.getContext())
                    v.rotation = 90f
                    v.layoutParams = layoutParams
                    v.setOnClickListener(onClickListener)
                    addViewInLayout(v, cell, v.getLayoutParams(), true)
                }

                //Set the image (color) of the cell and put its index in a tag, so we can retrieve the number of the clicked cell in the onClickListener.
                v.setImageResource(R.drawable.hexagon)//mImageIds.get(mImages.get(cell)))
                v.tag = cell

                //Set the bounds of the image, which will automatically be cropped in the available space.
                v.layout(
                    x_offset.toInt(),
                    y_offset.toInt(),
                    (x_offset + width).toInt(),
                    (y_offset + height).toInt()
                )
                x_offset += effectiveWidth
                ++cell
            }
            y_offset += effectiveHeight
            if (row %2 == 0) {
                x_offset_row += effectiveWidth / 2
            } else {
                x_offset_row -= effectiveWidth / 2
            }
        }

        //We updated all views, so it is not invalidated anymore.
        mSizeInvalidated = false
    }

    val onClickListener = OnClickListener { v -> Log.d("HEX", "Clicked on " + v?.tag.toString()) }

    private var mSizeInvalidated = true

    public override fun onDraw(c: Canvas) {
        super.onDraw(c)
        c.clipPath(hexagonBorderPath!!)
        c.drawColor(Color.YELLOW)
        c.save()
        c.clipPath(hexagonPath!!)
        c.drawColor(maskColor)
        c.save()
    }

    // getting the view size and default radius
    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        height = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        radius = height / 2 - 10
        calculatePath()
    }
}