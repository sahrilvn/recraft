package com.example.recraft

data class KontenEdukasi(
    val id : String?,
    val judul : String,
    val konten : String
){
    constructor(): this("","","") {

    }
}