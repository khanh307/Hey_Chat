package com.example.sudokugame


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity(), SudokuBoardView.OnTouchListener {
    private lateinit var viewModel : PlaySudokuViewModel
    private val buttons = listOf(btnone, btntwo, btnthree, btnfour, btnfive, btnsix, btnseven, btneight, btnnine)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = intent
        val level : Int = intent.getIntExtra("level", 9)

        sudokuBoardView.registerListener(this)

        viewModel = ViewModelProviders.of(this).get(PlaySudokuViewModel::class.java)
        viewModel.sudokuGame.selectedCellLiveData.observe(this, Observer { updateSelectedCellUI(it) })
        viewModel.sudokuGame.cellsLiveData.observe(this, Observer { updateCells(it) })

        buttons.forEachIndexed{index, button ->
            button.setOnClickListener{
                viewModel.sudokuGame.handleInput(index +1)
            }
        }
    }

    private fun updateCells(cells: List<Cell>?) = cells?.let {
        sudokuBoardView.updateCells(cells)
    }

    private fun updateSelectedCellUI(cell : Pair<Int, Int>?) = cell?.let {
        sudokuBoardView.updateSelectedCellUI(cell.first, cell.second)

    }

    override fun onCellTouched(row : Int, col : Int){
        viewModel.sudokuGame.updateSelectedCell(row, col)
    }
}