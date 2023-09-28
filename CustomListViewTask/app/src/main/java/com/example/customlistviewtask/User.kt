package com.example.customlistviewtask

class User(_name: String, _age: Int, _sex: String, _image: Int) {
    val name: String
    val age: Int
    val sex: String
    val image: Int

    init {
        name = _name
        age = _age
        sex = _sex
        image = _image
    }
}