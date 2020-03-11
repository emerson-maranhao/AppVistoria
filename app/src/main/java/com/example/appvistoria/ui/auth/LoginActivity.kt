package com.example.appvistoria.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.appvistoria.R
import com.example.appvistoria.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        if (edt_Cpf.equals(user.cpf) && edt_password.equals(user.password)){
//            btn_login.setOnClickListener(View.OnClickListener {
//                val intent = Intent(this, MainActivity::class.java)
//                // start your next activity
//                startActivity(intent)
//                finish()
//            })
//        }else{
//            Toast.makeText(this,"Usuário ou Senha Inválidos",Toast.LENGTH_LONG).show()
//        }

            btn_login.setOnClickListener(View.OnClickListener {

                loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


                loginViewModel.loginLiveData.observe(this, Observer {
                    //textView.text = it
                    it?.let { user ->

                    }
                })
                loginViewModel.getLogin()


                val intent = Intent(this, MainActivity::class.java)
                // start your next activity
                startActivity(intent)
                finish()

            })


    }
}
