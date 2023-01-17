package com.example.recraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class input_edukasi : AppCompatActivity(), View.OnClickListener {

    private lateinit var etJudul : EditText
    private lateinit var etKonten : EditText
    private lateinit var btnSave : Button
    private lateinit var ref : DatabaseReference
    private lateinit var listEdu : ListView
    private lateinit var eduList: MutableList<input_edukasi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_edukasi)

        ref = FirebaseDatabase.getInstance().getReference("edukasi")

        etJudul = findViewById(R.id.etJudul)
        etKonten = findViewById(R.id.etKonten)
        btnSave = findViewById(R.id.btnSimpan)
        listEdu = findViewById(R.id.lvEdukasi)
        btnSave.setOnClickListener(this)

        eduList = mutableListOf()

    }

    override fun onClick(p0: View?) {
        saveData()
    }

    private fun saveData() {
        val judul = etJudul.text.toString().trim()
        val konten = etKonten.text.toString().trim()

        if (judul.isEmpty()){
            etJudul.error = "Isi Judul!"
            return
        }

        if (konten.isEmpty()){
            etKonten.error = "Isi Konten!"
            return
        }


        val eduId = ref.push().key
        val edu = KontenEdukasi(eduId, judul, konten )
        if (eduId != null) {
            ref.child(eduId).setValue(edu).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data Berhasil", Toast.LENGTH_SHORT).show()
            }
        }

    }
}