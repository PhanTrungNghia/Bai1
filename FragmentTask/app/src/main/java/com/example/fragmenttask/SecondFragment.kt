package com.example.fragmenttask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Second Fragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("Second Fragment", "onCreateView called")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Second Fragment", "onViewCreated called")
    }

    override fun onStart() {
        super.onStart()
        Log.e("Second Fragment", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Second Fragment", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Second Fragment", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Second Fragment", "onStop called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("Second Fragment", "onDestroyView called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Second Fragment", "onDestroy called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("Second Fragment", "onDetach called")
    }

}