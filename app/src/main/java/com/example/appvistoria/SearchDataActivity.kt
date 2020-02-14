package com.example.appvistoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SearchDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_data)
        val btn_search: Button = findViewById(R.id.btn_search)
        btn_search.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(this, AddSurveyActivity::class.java)
            // start your next activity

            startActivity(intent)
        })
    }
}
