package com.example.sudokuapplication

class Cell(
    val row: Int,
    val col: Int,
    var value: Int,
    var board : Int,
    var isStartingCell: Boolean = false,
    var notes : MutableSet<Int> = mutableSetOf()
) {
}