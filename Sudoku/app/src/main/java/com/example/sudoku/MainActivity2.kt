package com.example.sudoku

import android.arch.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity(), SudokuBoardView.OnTouchListener {

    private lateinit var viewModel : PlaySudokuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = intent
        val level : Int = intent.getIntExtra("level", 9)

        sudokuBoardView.registerListener(this)

        viewModel = ViewModelProviders.of(this as Fragment).get(PlaySudokuViewModel::class.java)
        viewModel.sudokuGame.selectedCellLiveData.observe(this, Observer { updateSelectedCellUI(it) })

    }

    private fun updateSelectedCellUI(cell : Pair<Int, Int>?) = cell?.let {
            sudokuBoardView.updateSelectedCellUI(cell.first, cell.second)

    }

    override fun onCellTouched(row : Int, col : Int){
        viewModel.sudokuGame.updateSelectedCell(row, col)
    }
}

