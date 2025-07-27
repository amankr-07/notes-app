package com.myapp.notes.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.myapp.notes.R

class UpdateActivity : AppCompatActivity() {

    lateinit var editTextTitleUpdate : EditText
    lateinit var editTextDescriptionUpdate : EditText
    lateinit var buttonCancelUpdate : Button
    lateinit var buttonSaveUpdate : Button

    var currentId = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)

        supportActionBar?.title = "Update Note"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextTitleUpdate = findViewById(R.id.editTextTitleUpdate)
        editTextDescriptionUpdate = findViewById(R.id.editTextDescriptionUpdate)
        buttonCancelUpdate = findViewById(R.id.buttonCancelUpdate)
        buttonSaveUpdate = findViewById(R.id.buttonSaveUpdate)

        getAndSetData()

        buttonCancelUpdate.setOnClickListener {
            Toast.makeText(applicationContext, "Nothing update", Toast.LENGTH_SHORT).show()
            finish()
        }

        buttonSaveUpdate.setOnClickListener {
            updateNote()
        }

    }

    fun updateNote() {

        val updatedTitle = editTextTitleUpdate.text.toString()
        val updatedDescription = editTextDescriptionUpdate.text.toString()

        val intent = Intent()
        intent.putExtra("updatedTitle", updatedTitle)
        intent.putExtra("updatedDescription", updatedDescription)
        if(currentId != -1) {

            intent.putExtra("noteId", currentId)
            setResult(RESULT_OK, intent)
            finish()

        }

    }

    fun getAndSetData() {

        // get
        val currentTitle = intent.getStringExtra("currentTitle")
        val currentDescription = intent.getStringExtra("currentDescription")
        currentId = intent.getIntExtra("currentId", -1)

        // set
        editTextTitleUpdate.setText(currentTitle)
        editTextDescriptionUpdate.setText(currentDescription)

    }

}