package com.example.appvistoria.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appvistoria.view.MainActivity
import com.example.appvistoria.R
import kotlinx.android.synthetic.main.activity_add_survey.*

class AddSurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)

        btn_salvar_vistoria.setOnClickListener {
            val listItems = arrayOf("Aprovada", "Pendente", "Reprovada")
            val mBuilder = AlertDialog.Builder(this@AddSurveyActivity)
            mBuilder.setTitle("Status da Vistoria")
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                val txtView: String
                txtView = listItems[i]
                Toast.makeText(this, "$txtView", Toast.LENGTH_LONG).show()
                //dialogInterface.dismiss()
            }
            mBuilder.setPositiveButton("Ok") { dialog, which ->
                // Do something when click the positive button
                dialog.cancel()
                Toast.makeText(this, "Vistoria Salva com Sucesso!", Toast.LENGTH_LONG).show()
                finish()

                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

            }
            // Set the neutral/cancel button click listener
            mBuilder.setNeutralButton("Cancelar") { dialog, which ->
                // Do something when click the neutral button
                dialog.cancel()
            }

            val mDialog = mBuilder.create()
            mDialog.show()

        }
    }


}
