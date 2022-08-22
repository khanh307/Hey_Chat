package com.example.sudokuapplication


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SudokuBoardView.OnTouchListener{
    private lateinit var viewModel: PlaySudokuViewModel
    private lateinit var buttons : List<Button>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Log.d("AAA", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d("AAA", "onStart")
        sudokuBoardView.registerListener(this)
        viewModel = ViewModelProviders.of(this).get(PlaySudokuViewModel::class.java)

        viewModel.sudokuGame.selectedCellLiveData.observe(this, Observer { updateSelectedCellUI(it) })
        viewModel.sudokuGame.cellsLiveData.observe(this, Observer { updateCells(it) })
        viewModel.sudokuGame.isTakingNotesLiveData.observe(this, Observer { updateNoteTakingUI(it) })
        viewModel.sudokuGame.highlightedKeysLiveData.observe(this, Observer { updateHightlightedKeys(it) })

        buttons = listOf(
            oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton
        )

        buttons.forEachIndexed{index, button ->
            button.setOnClickListener{
                viewModel.sudokuGame.handleInput(index +1)
            }
        }

        noteButton.setOnClickListener {
            viewModel.sudokuGame.changeNoteTakingState()
        }

        deleteButton.setOnClickListener {
            viewModel.sudokuGame.delete()
        }
        newButton.setOnClickListener {
            onStop()
            onRestart()
            onStart()
        }


    }

    override fun onStop() {
        super.onStop()
        Log.d("AAA", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("AAA", "onRestart")
        viewModel = ViewModelProviders.of(this).get(PlaySudokuViewModel::class.java)
        viewModel.sudokuGame.selectedCellLiveData = MutableLiveData<Pair<Int, Int>>()
        viewModel.sudokuGame.cellsLiveData = MutableLiveData<List<Cell>>()
        viewModel.sudokuGame.isTakingNotesLiveData = MutableLiveData<Boolean>()
        viewModel.sudokuGame.highlightedKeysLiveData = MutableLiveData<Set<Int>>()

    }

    override fun onPause() {
        super.onPause()
        Log.d("AAA", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("AAA", "onResume")
    }




    private fun updateHightlightedKeys(set: Set<Int>?) = set?.let {
        buttons.forEachIndexed { index, button ->
            if(set.contains(index + 1)){
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
            } else{
                button.setBackgroundResource(R.drawable.custom_num)
            }
        }
    }

    private fun updateNoteTakingUI(isNoteTaking: Boolean?) = isNoteTaking?.let{

        if(it){
            noteButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
        } else{
            noteButton.setBackgroundResource(R.drawable.custom_num)
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
