package com.example.customlistviewtask

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MyArrayAdapter
    private lateinit var lvUser: ListView
    private lateinit var btnAdd: Button
    private lateinit var btnNoThanks: Button
    private lateinit var btnAddUser: Button
    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtSex: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btn_add)

        btnAdd.setOnClickListener {
            Toast.makeText(this, "Added", Toast.LENGTH_LONG).show()
            openAddUserDialog(Gravity.CENTER)
        }

        mAdapter = Constants.getAdapter(this)

        lvUser = findViewById(R.id.lv_user)

        lvUser.adapter = mAdapter
    }

    private fun openAddUserDialog(gravity: Int) {
        // Initializing Dialog
        val mDialog = Dialog(this)

        setUpSettingsDialog(mDialog, gravity)

        initUIDialog(mDialog)

        handleActionDialog(mDialog)

        // Display Dialog
        mDialog.show()
    }

    private fun handleActionDialog(mDialog: Dialog) {
        btnNoThanks.setOnClickListener {
            mDialog.dismiss()
        }

        btnAddUser.setOnClickListener {
            addUser()
        }
    }

    private fun initUIDialog(mDialog: Dialog) {
        btnNoThanks = mDialog.findViewById(R.id.btn_no_thanks)
        btnAddUser = mDialog.findViewById(R.id.btn_add_user)

        edtName = mDialog.findViewById(R.id.edt_name)
        edtAge = mDialog.findViewById(R.id.edt_age)
        edtSex = mDialog.findViewById(R.id.edt_sex)
    }

    private fun setUpSettingsDialog(dialog: Dialog, gravity: Int) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.layout_dialog_user)

        val window: Window? = dialog.window

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        window?.setGravity(gravity)

        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true)
        }
    }

    private fun addUser() {
        val mName: String = edtName.text.toString().trim()
        val mAge: String = edtAge.text.toString().trim()
        val mSex: String = edtSex.text.toString().trim()

        Constants.mList.add(User(
            mName,
            mAge.toInt(),
            mSex,
            R.drawable.dragonball_image
        ))

        Toast.makeText(this, "Add Successfully", Toast.LENGTH_SHORT).show()

        // Refresh List View after update data
        lvUser.adapter = mAdapter

        // Refresh EditText
        edtName.setText("")
        edtAge.setText("")
        edtSex.setText("")
    }
}