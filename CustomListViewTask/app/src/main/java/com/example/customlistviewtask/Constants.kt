package com.example.customlistviewtask


import android.app.Activity

object Constants {
    val mList: MutableList<User> = mutableListOf<User>()
    private val mName = mutableListOf<String>("Son Goku", "Vegeta", "Piccolo", "Saitama", "Asta")
    private val mAge = mutableListOf<Int>(43, 48, 31, 25, 17)
    private val mSex = mutableListOf<String>("male", "male", "male", "male", "male")
    private val mImage = mutableListOf<Int>(
        R.drawable.songoku,
        R.drawable.vegeta,
        R.drawable.piccolo,
        R.drawable.saitama,
        R.drawable.asta
    )

    fun getAdapter(activity: Activity): MyArrayAdapter {

        for (i in 0 until mName.size) {
            mList.add(
                User(
                    mName[i],
                    mAge[i],
                    mSex[i],
                    mImage[i]
                )
            )
        }
        return MyArrayAdapter(activity, R.layout.layout_custom, mList)
    }
}
