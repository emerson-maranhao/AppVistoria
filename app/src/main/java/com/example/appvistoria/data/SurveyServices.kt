package com.example.appvistoria.data

import retrofit2.Call
import retrofit2.http.GET

interface SurveyServices {
   //@GET("listall")
    @GET("details")

    fun getSurveys(
        //@Query("api-key") apiKey: String = "dYvSDzxECNAYUvJ2btXR2CyNnbUwadjdjTOs",
        //@Query("list") list: String = "hardcover-fiction"
        //@Query("") list: String = ""
    ): Call<List<Survey>>

}