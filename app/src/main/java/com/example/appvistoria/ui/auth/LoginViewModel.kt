package com.example.appvistoria.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appvistoria.data.User


class LoginViewModel : ViewModel() {

    val loginLiveData: MutableLiveData<User> = MutableLiveData()

    fun getLogin() {
        loginLiveData.value = createFakeLogin()[1]
    }

    fun createFakeLogin(): List<User> {
        return listOf(
            return listOf(
                User(
                    "11111111111",
                    "123456",
                    "Usuario1"
                ),
                User(
                    "22222222222",
                    "123456",
                    "Usuario2"

                ),
                User(
                    "33333333333",
                    "123456",
                    "Usuario3"

                )
            )
        )


    }
}


