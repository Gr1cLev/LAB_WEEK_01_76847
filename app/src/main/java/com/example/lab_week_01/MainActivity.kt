package com.example.lab_week_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameDisplay = findViewById<TextView>(R.id.name_display)
        val nameSubmit  = findViewById<Button>(R.id.name_submit)

        nameSubmit.setOnClickListener {
            val nameInput = findViewById<TextInputEditText>(R.id.name_input)
                ?.text?.toString()?.trim().orEmpty()
            val studentInput = findViewById<TextInputEditText>(R.id.student_input)
                ?.text?.toString()?.trim().orEmpty()

            // 1) Validasi name tidak boleh kosong (mengikuti pola di modul)
            if (nameInput.isEmpty()) {
                Toast.makeText(this, getString(R.string.name_empty), Toast.LENGTH_LONG)
                    .apply { setGravity(Gravity.CENTER, 0, 0); show() }
                return@setOnClickListener
            }

            // 2) Validasi student number: HARUS tepat 11 digit angka
            val isStudentValid = studentInput.matches(Regex("^\\d{11}$"))
            if (!isStudentValid) {
                Toast.makeText(this, getString(R.string.student_error), Toast.LENGTH_LONG)
                    .apply { setGravity(Gravity.CENTER, 0, 0); show() }
                return@setOnClickListener
            }

            // 3) Jika lolos semua, tampilkan ke TextView
            nameDisplay?.text = "${getString(R.string.name_greet)} $nameInput\n" +
                    "${getString(R.string.student_greet)} $studentInput"
        }
    }
}