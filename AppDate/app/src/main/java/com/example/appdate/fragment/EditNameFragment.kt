package com.example.appdate.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appdate.R

class EditNameFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.dialog_edit_name, container, false)

        var editNameUser : ImageButton = view.findViewById(R.id.editNameUser)

        editNameUser.setOnClickListener {
            var transition : FragmentTransaction = requireFragmentManager().beginTransaction()
            transition.replace(R.id.frame, Edit())
            transition.commit()
        }

        return view
    }
}