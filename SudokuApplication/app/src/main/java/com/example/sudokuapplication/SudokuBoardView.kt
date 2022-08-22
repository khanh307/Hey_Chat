package com.example.sudokuapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.min

class SudokuBoardView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var size = 9
    private var sqrtSize = 3
    private var cellSizePixels = 0F
    private var noteSizePixels = 0F
    private var selectedRow = -1
    private var selectedCol = -1
    private var listener : SudokuBoardView.OnTouchListener? = null
    private var isFinish = true

    private var cells : List<Cell>? = null

    private val thickLinePaint = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.parseColor("#616266")
        strokeWidth = 5F
    }

    private val fillPanit = Paint().apply{
        style = Paint.Style.FILL
        color = Color.parseColor("#bbdefa")
        alpha = 150
    }

    private val thinLinePaint = Paint().apply{
        style = Paint.Style.STROKE
        color = Color.parseColor("#c4ccd7")
        strokeWidth = 2F
    }

    private val selectedCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#bbdefa")
    }

    private val duplicateCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#c8d0dd")
    }

    private val errorCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#fdd4dc")

    }

    private val conflictingCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#e2e7ed")
    }

    private val textPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        textSize = 24F
    }

    private val textPaintTrue = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.GREEN
        textSize = 24F
    }

    private val textPaintFalse = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.RED
        textSize = 24F
    }

    private val startingCellTextPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        textSize = 32F
        typeface = Typeface.DEFAULT_BOLD
    }

    private val startingCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.WHITE

    }

    private val noteTextPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var sizePixels = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas) {
        updateMeasurerements(width)

        fillCells(canvas)
        drawLine(canvas)
        drawText(canvas)
        if(isFinish){
            drawFinish(canvas)
        }
    }

    private fun drawFinish(canvas: Canvas){
        val textBounds = Rect()
        textPaintTrue.getTextBounds("Hoàn thành", 0, "Hoàn thành".length, textBounds)
        val textWidth = noteTextPaint.measureText("Hoàn thành")
        val textHeight = textBounds.height()
        canvas.drawRect(0F+1, 0F +1, width.toFloat()-1, height.toFloat()-1, fillPanit)

        canvas.drawText(
            "Hoàn thành",
            width.toFloat() / 2 - textWidth,
            height.toFloat() / 2- textHeight,
            textPaintTrue
        )
    }

    private fun updateMeasurerements(width: Int) {
        cellSizePixels = (width/size).toFloat()
        noteSizePixels = cellSizePixels/ sqrtSize.toFloat()
        noteTextPaint.textSize = cellSizePixels / sqrtSize.toFloat()
        textPaint.textSize = cellSizePixels / 1.5F
        startingCellTextPaint.textSize = cellSizePixels / 1.5F
        textPaintTrue.textSize = cellSizePixels / 1.5F
        textPaintFalse.textSize = cellSizePixels / 1.5F
    }

    private fun fillCells(canvas: Canvas) {

        cells?.forEach{
            val r = it.row
            val c = it.col
            if(r == selectedRow && c == selectedCol){
                fillCell(canvas, r, c, selectedCellPaint)
            } else if(r == selectedRow || c == selectedCol){
                fillCell(canvas, r, c, conflictingCellPaint)
            } else if(r / sqrtSize == selectedRow / sqrtSize && c / sqrtSize == selectedCol / sqrtSize){
                fillCell(canvas, r, c, conflictingCellPaint)
            }
        }
    }
    private fun fillCell(canvas: Canvas, r: Int, c: Int, paint: Paint) {
        canvas.drawRect(c *cellSizePixels, r * cellSizePixels, (c+1)*cellSizePixels, (r+1)*cellSizePixels, paint)

    }

    private fun drawLine(canvas: Canvas) {
        canvas.drawRect(0F+1, 0F +1, width.toFloat()-1, height.toFloat()-1, thickLinePaint)

        for(i in 1 until size){
            val paintToUse = when (i % sqrtSize){
                0 -> thickLinePaint
                else -> thinLinePaint
            }
            canvas.drawLine(i * cellSizePixels, 0F, i * cellSizePixels, height.toFloat(), paintToUse)
            canvas.drawLine(0F ,i * cellSizePixels, width.toFloat() ,i * cellSizePixels, paintToUse)
        }
    }


    private fun drawText(canvas: Canvas){
        var num = 0;
        if(selectedRow != -1){
            num = cells?.get(selectedRow*9 + selectedCol)?.value!!
        }
        isFinish = true
       cells?.forEach { cell ->
            val value = cell.value

            val textBounds = Rect()
            if(cell.value != cell.board){
                isFinish = false
            }
            if(value == 0){
                cell.notes.forEach{note ->
                    val rowInCell = (note - 1) / sqrtSize
                    val colInCell = (note - 1) % sqrtSize
                    val valueString = note.toString()
                    noteTextPaint.getTextBounds(valueString, 0, valueString.length, textBounds)
                    val textWidth = noteTextPaint.measureText(valueString)
                    val textHeight = textBounds.height()

                    canvas.drawText(
                        valueString,
                        (cell.col * cellSizePixels) + (colInCell * noteSizePixels) + noteSizePixels / 2 - textWidth / 2f,
                        (cell.row * cellSizePixels) + (rowInCell * noteSizePixels) + noteSizePixels / 2 + textHeight / 2f,
                        noteTextPaint
                    )
                }
            } else {

                val row = cell.row
                val col = cell.col
                val valueString = cell.value.toString()
                val valueBoard = cell.board.toString()
//                val paintToUse = if (cell.isStartingCell) startingCellTextPaint else textPaint
                var paintToUse = if (cell.isStartingCell) startingCellTextPaint else textPaint
                paintToUse.getTextBounds(valueString, 0, valueString.length, textBounds)

                val textWidth = textPaint.measureText(valueString)
                val textHeight = textBounds.height()

                if(cell.value == num){

                    if(row != selectedRow || col != selectedCol){
                        //fillCell(canvas, row, col, duplicateCellPaint)
                        canvas.drawRect(col *cellSizePixels + 2, row * cellSizePixels + 2, (col+1)*cellSizePixels  - 2, (row+1)*cellSizePixels - 2, duplicateCellPaint)

                    }
                    if(row == selectedRow &&  selectedCol != col){

                        canvas.drawRect(col *cellSizePixels + 2, row * cellSizePixels + 2, (col+1)*cellSizePixels  - 2, (row+1)*cellSizePixels - 2, errorCellPaint)
                    }
                    if(row != selectedRow &&  selectedCol == col){
                        canvas.drawRect(col *cellSizePixels + 2, row * cellSizePixels + 2, (col+1)*cellSizePixels  - 2, (row+1)*cellSizePixels - 2, errorCellPaint)
                    }
                }

                canvas.drawText(
                    valueString,
                    (col * cellSizePixels) + cellSizePixels / 2 - textWidth / 2,
                    (row * cellSizePixels) + cellSizePixels / 2 + textHeight / 2,
                    paintToUse
                )

            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(!isFinish){
            return when(event.action){
                MotionEvent.ACTION_DOWN ->{
                    handleTouchEvent(event.x, event.y)
                    true
                } else -> false
            }
        }
        return false
    }

    private fun handleTouchEvent(x: Float, y: Float) {
        val possibleSeletedRow = (y / cellSizePixels).toInt()
        val possibleSeletedCol = (x / cellSizePixels).toInt()
//        selectedRow = possibleSeletedRow
//        selectedCol = possibleSeletedCol
        listener?.onCellTouched(possibleSeletedRow, possibleSeletedCol)
    }

    fun updateSelectedCellUI(row: Int, col: Int) {
        selectedRow = row
        selectedCol = col
        invalidate()
    }

    fun updateCells(cells : List<Cell>){
        this.cells = cells
        invalidate()
    }

    fun registerListener(listener : OnTouchListener){
        this.listener = listener
    }

    interface OnTouchListener{
        fun onCellTouched(row : Int, col : Int)
    }

}