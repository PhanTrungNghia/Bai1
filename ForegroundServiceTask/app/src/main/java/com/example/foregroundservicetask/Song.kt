package com.example.foregroundservicetask

import java.io.Serializable

class Song(_title: String, _single: String, _image: Int, _resource: Int): Serializable{

    var title: String
    var single: String
    var image: Int
    var resource: Int

    init {
        title = _title
        single =_single
        image = _image
        resource = _resource
    }
}