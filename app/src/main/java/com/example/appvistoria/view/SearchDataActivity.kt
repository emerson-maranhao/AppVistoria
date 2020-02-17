package com.example.appvistoria.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appvistoria.R
import com.example.appvistoria.view.AddSurveyActivity
import kotlinx.android.synthetic.main.activity_search_data.*


class SearchDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_data)
        //val btn_search: Button = findViewById(R.id.btn_search)

//        btn_search.setOnClickListener(View.OnClickListener { view: View? ->
//            val intent = Intent(this, AddSurveyActivity::class.java)
//            // start your next activity
//
//            startActivity(intent)
//        })


        btn_search.setOnClickListener {
            if (progressBar != null) {
                val visibility =
                    if (progressBar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                progressBar.visibility = visibility
                val handler = Handler()

                handler.postDelayed({
                    progressBar.visibility = View.GONE
                    val intent = Intent(this, AddSurveyActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)


                }, 1000)


//                val btn_search = if (progressBar.visibility == View.GONE) "SHOW PROGRESSBAR" else "HIDE PROGRESSBAR"
//                btn.text = btnText
            }
        }

    }
}
