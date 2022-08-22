package com.example.sudokuapplication

class SudokuSolver(var size : Int, var board : Array<IntArray>){
    fun isSafe(row: Int, col: Int, num: Int): Boolean {
        //Kiểm tra hàng
        for (d in 0 until board.size) {
            //if trong hàng tồn tại num return false;
            if (board[row][d] === num) {
                return false
            }
        }

        //Kiểm tra cột
        for (r in 0 until board.size) {
            //if trong cột tồn tại num trả về false
            if (board[r][col] === num) {
                return false
            }
        }

        //Kiểm tra khối
        val sizeBox = Math.sqrt(board.size.toDouble()).toInt()
        val boxRowStart = row - row % sizeBox
        val boxColStart = col - col % sizeBox
        //Duyệt qua khối
        for (h in boxRowStart until boxRowStart + sizeBox) {
            for (c in boxColStart until boxColStart + sizeBox) {
                if (board[h][c] === num) {
                    return false
                }
            }
        }

        //if num không tồn tại trong board trả về true
        return true
    }

    fun solverSudoku(length: Int): Boolean {
        var row = -1
        var col = -1
        var isEmpty = false
        //duyệt qua mảng board
        for (i in 0 until length) {
            for (j in 0 until length) {
                //if [i,j] chưa có số
                if (board[i][j] === 0) {
                    row = i
                    col = j
                    //đánh dấu có ô trống và break
                    isEmpty = true
                    break
                }
            }
            //Nếu có ô trống thoát ra
            if (isEmpty) {
                break
            }
        }

        //nếu ko có ô trống thoát - đã điền hết
        if (!isEmpty) {
            return true
        }

        //Ngược lại duyệt qua từ 1 đến số lớn nhất được điền vào
        for (num in 1..length) {
            //Nếu num hợp lệ
            //Đưa num vào board và đệ quy
            if (isSafe(row, col, num)) {
                board[row][col] = num
                val check = solverSudoku(length)
                //Nếu tìm được trả về true;
                //Ngược lại nến điền num mà các ô số sau ko điền được thì gán lại
                if (check) {
                    return true
                } else {
                    board[row][col] = 0 // replace it
                }
            }
        }
        return false
    }
}