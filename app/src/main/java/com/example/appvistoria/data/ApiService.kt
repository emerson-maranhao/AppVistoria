package com.example.appvistoria.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

    object ApiService {

        private fun initRetrofit(): Retrofit {

            return Retrofit.Builder()
               // .baseUrl("http://10.11.3.205:3333/")
                .baseUrl("http://10.11.1.76:3000/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        val service: SurveyServices =initRetrofit().create(SurveyServices::class.java)


}



