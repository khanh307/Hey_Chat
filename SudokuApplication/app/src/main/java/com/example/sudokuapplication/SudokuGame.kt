package com.example.sudokuapplication

import android.arch.lifecycle.MutableLiveData

class SudokuGame {
    var selectedCellLiveData = MutableLiveData<Pair<Int, Int>>()
    var cellsLiveData = MutableLiveData<List<Cell>>()
    var isTakingNotesLiveData = MutableLiveData<Boolean>()
    var highlightedKeysLiveData = MutableLiveData<Set<Int>>()

    private var selectedRow = -1
    private var selectedCol = -1
    private var isTakingNotes = false

    private var board : Board


    init {

        var array : Array<IntArray> = Array(9, {IntArray(9)})
        var randomSudoku : RandomSudoku = RandomSudoku()
        array = randomSudoku.randomOneRow(9)!!
        var sudokuSolver = SudokuSolver(9, array)
        sudokuSolver.solverSudoku(9)
        var min = 5
        var max = 6

        var arrayIndex = randomSudoku.arrayIndex(max, min, array)!!
        val cells = List(9 * 9){i -> Cell(i / 9, i % 9, arrayIndex[i/9][i%9], array[i/9][i%9])}
        for(i in 0 until 9){
            for(j in 0 until 9){
                if(arrayIndex[i][j] != 0){
                    cells[(i * 9) + (j % 9)].isStartingCell = true
                }
            }
        }

        board = Board(9, cells)
        selectedCellLiveData.postValue(Pair(selectedRow, selectedCol))
        cellsLiveData.postValue(board!!.cells)
        isTakingNotesLiveData.postValue(isTakingNotes)
    }

    fun handleInput(number : Int){
        if(selectedRow == -1 || selectedCol == -1) return
        val cell = board.getCell(selectedRow, selectedCol)
        if(cell.isStartingCell) return
        if(isTakingNotes){
            if(cell.notes.contains(number)){
                cell.notes.remove(number)
            } else {
                cell.notes.add(number)
            }
            highlightedKeysLiveData.postValue(cell.notes)
        } else {
            cell.value = number

        }
        cellsLiveData.postValue(board.cells)
    }

    fun updateSelectedCell(row : Int, col : Int){
        var cell = board.getCell(row, col)
        if(!cell.isStartingCell) {
            selectedRow = row
            selectedCol = col
            selectedCellLiveData.postValue(Pair(row, col))

            if(isTakingNotes){
                highlightedKeysLiveData.postValue(cell.notes)
            }
        }
    }

    fun changeNoteTakingState(){
        if(selectedRow == -1 || selectedCol == - 1) return
        isTakingNotes = !isTakingNotes
        isTakingNotesLiveData.postValue(isTakingNotes)
        val curNotes = if(isTakingNotes){
            board.getCell(selectedRow, selectedCol).notes
        }else{
            setOf<Int>()
        }
        highlightedKeysLiveData.postValue(curNotes)
    }

    fun delete(){
        if(selectedRow == -1) return
        val cell = board.getCell(selectedRow, selectedCol)
        if(isTakingNotes){
            cell.notes.clear()
            highlightedKeysLiveData.postValue(cell.notes)
        } else {
            cell.value = 0
        }
        cellsLiveData.postValue(board.cells)
    }

}