package com.example.customlistviewtask

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyArrayAdapter(
    private val context: Activity,
    private val mLayout: Int,
    private val mList: MutableList<User>
    ): ArrayAdapter<User>(context, mLayout, mList){

    private lateinit var imgUser: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvSex: TextView
    private lateinit var tvIdentity: TextView

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val mInflater: LayoutInflater = context.layoutInflater
        view = mInflater.inflate(mLayout, null)

        val mUser = mList[position]

        initUI(view)

        setData(mUser, position + 1)

        return view!!
    }

    private fun setData(mUser: User, position: Int) {
        imgUser.setImageResource(mUser.image)
        tvName.text = tvName?.text.toString().trim() + " " + mUser.name
        tvAge.text = tvAge?.text.toString().trim() + " " + mUser.age
        tvSex.text = tvSex?.text.toString().trim() + " " + mUser.sex
        tvIdentity.text = tvIdentity?.text.toString().trim() + " " + position
    }

    private fun initUI(view: View?) {
        imgUser = view!!.findViewById(R.id.img_user)
        tvName = view.findViewById(R.id.tv_name)
        tvAge = view.findViewById(R.id.tv_age)
        tvSex = view.findViewById(R.id.tv_sex)
        tvIdentity = view.findViewById(R.id.tv_identity)
    }
}