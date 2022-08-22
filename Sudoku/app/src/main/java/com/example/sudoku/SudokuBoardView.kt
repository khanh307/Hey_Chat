package com.example.sudoku

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class SudokuBoardView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){
    private var size = 9
    private var sqrtSize = 3
    private var cellSizePixels = 0F
    private var selectedRow = -1
    private var selectedCol = -1
    private var listener : SudokuBoardView.OnTouchListener? = null


    public fun setSize(level : Int){
        size = level
    }


    private val thickLinePain = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 5F
    }

    private val thinLinePain = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 2F
    }

    private val selectedCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.GRAY
    }
    private val conflictingCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#efedef")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var sizePixels = Math.min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas) {
        cellSizePixels = (width/size).toFloat()
        fillCells(canvas)
        drawLine(canvas)
    }

    private fun fillCells(canvas: Canvas) {
        if(selectedRow == -1 || selectedCol == -1 ) return
        for(r in 0..size){
            for(c in 0..size){
                if(r == selectedRow && c == selectedCol){
                    fillCell(canvas, r, c, selectedCellPaint)
                } else if(r == selectedRow || c == selectedCol){
                    fillCell(canvas, r, c, conflictingCellPaint)
                } else if(r / sqrtSize == selectedRow / sqrtSize && c / sqrtSize == selectedCol / sqrtSize){
                    fillCell(canvas, r, c, conflictingCellPaint)
                }
            }
        }
    }

    private fun fillCell(canvas: Canvas, r: Int, c: Int, paint: Paint) {
        canvas.drawRect(c*cellSizePixels, r * cellSizePixels, (c+1)*cellSizePixels, (r+1)*cellSizePixels, paint)

    }

    private fun drawLine(canvas: Canvas) {
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), thickLinePain)

        for(i in 1 until size){
            val painToUse = when(i % sqrtSize){
                0 -> thickLinePain
                else -> thinLinePain
            }
            canvas.drawLine(i*cellSizePixels, 0F, i*cellSizePixels, height.toFloat(), painToUse)
            canvas.drawLine(0F, i*cellSizePixels, width.toFloat(), i*cellSizePixels, painToUse)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when(event.action){
            MotionEvent.ACTION_DOWN ->{
                handleTouchEvent(event.x, event.y)
                true
            } else -> false
        }
    }

    private fun handleTouchEvent(x: Float, y: Float) {
        val possibleSeletedRow = (y / cellSizePixels).toInt()
        val possibleSeletedCol = (x / cellSizePixels).toInt()
        listener?.onCellTouched(possibleSeletedRow, possibleSeletedCol)
    }

    fun updateSelectedCellUI(row: Int, col: Int) {
        selectedRow = row
        selectedCol = col
        invalidate()
    }

    fun registerListener(listener : OnTouchListener){
        this.listener = listener
    }

    interface OnTouchListener{
        fun onCellTouched(row : Int, col : Int)
    }
}