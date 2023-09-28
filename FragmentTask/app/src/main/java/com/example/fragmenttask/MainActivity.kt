package com.example.fragmenttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), InterfaceSetCurrentFragment {

    private lateinit var firstFragment: FirstFragment
    lateinit var secondFragment: SecondFragment
    lateinit var thirdFragment: ThirdFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstFragment = FirstFragment.getInstance(this)
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        setCurrentFragment(firstFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_item_home -> setCurrentFragment(firstFragment)
                R.id.menu_item_messages -> setCurrentFragment(secondFragment)
                R.id.menu_item_profile -> setCurrentFragment(thirdFragment)
            }
            //lambda function will always return the last line of function which is true
            true
        }

        bottomNavigationView.getOrCreateBadge(R.id.menu_item_messages).apply {
            number = 10
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv, fragment)
            commit()
        }
    }

    override fun setFragmentFromFragment(flagRequest: Boolean) {
        if(flagRequest){
            setCurrentFragment(secondFragment)
        }
    }
}