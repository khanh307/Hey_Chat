package com.example.sudokuapplication

import java.util.*

class RandomSudoku {
    private val rand: Random? = Random()

    fun randomOneRow(n: Int): Array<IntArray>? {
        val board = Array(n) {
            IntArray(
                n
            )
        }
        val numbers = ArrayList<Int>()
        for (i in 1 until n + 1) {
            numbers.add(i)
        }
        Collections.shuffle(numbers)
        for (j in 0 until n) {
            board[0][j] = numbers[j]
        }
        for(i in 1 until n){
            for(j in 0 until n){
                board[i][j] = 0
            }
        }
        return board
    }

    fun randomNum(max: Int, min: Int): Int {
        return rand!!.nextInt(max - min + 1) + min
    }

    //Random vị trí xuất hiện của ô số
    fun randomIndex(num: Int, length: Int): IntArray? {
        val A = IntArray(num)
        var i = 0
        while (i < num) {
            val row = rand!!.nextInt(length)
            if (!checkArray(A, i, row)) {
                A[i] = row
            } else {
                i--
            }
            i++
        }
        return A
    }

    fun arrayIndex(max: Int, min: Int, array : Array<IntArray>): Array<IntArray>? {
        val board = Array(9) {
            IntArray(
                9
            )
        }
        for (i in 0..8) {
            val num = randomNum(max, min)
            var A: IntArray? = IntArray(num)
            A = randomIndex(num, 9)
            for (j in 0 until num) {
                board[i][A!![j]] = array[i][A[j]]
            }
        }
        return board
    }

    private fun checkArray(A: IntArray, n: Int, row: Int): Boolean {
        for (i in 0 until n) {
            if (A[i] == row) {
                return true
            }
        }
        return false
    }
}