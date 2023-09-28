package com.example.fragmenttask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    lateinit var interfaceSetCurrentFragment: InterfaceSetCurrentFragment

    companion object{
        fun getInstance(_interfaceSetCurrentFragment: InterfaceSetCurrentFragment) =
            FirstFragment().apply {
                interfaceSetCurrentFragment = _interfaceSetCurrentFragment
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("First Fragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("First Fragment", "onCreateView called")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("First Fragment", "onViewCreated called")

        val btnReplaceFragment = view.findViewById<Button>(R.id.btn_replace_fragment)

        btnReplaceFragment.setOnClickListener {
            interfaceSetCurrentFragment.setFragmentFromFragment(true)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("First Fragment", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.e("First Fragment", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.e("First Fragment", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.e("First Fragment", "onStop called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("First Fragment", "onDestroyView called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("First Fragment", "onDestroy called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("First Fragment", "onDetach called")
    }
}