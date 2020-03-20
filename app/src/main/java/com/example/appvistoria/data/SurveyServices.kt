package com.example.appvistoria.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface SurveyServices {
    @GET("listall")
    //@GET("details")

    fun getSurveys(
        //@Query("api-key") apiKey: String = "dYvSDzxECNAYUvJ2btXR2CyNnbUwadjdjTOs",
        //@Query("list") list: String = "hardcover-fiction"
        //@Query("") list: String = ""
    ): Call<List<Survey>>

    @POST("survey")
    fun insertSurvey(@Body survey: Survey):Call<Survey>

    // new code for multiple files
    @Multipart
    @POST("upload")
    open fun uploadFileWithPartMap(
        @PartMap partMap: Map<String?, RequestBody?>?,
        @Part file: MultipartBody.Part?,
        @Part file1: MultipartBody.Part?,
        @Part file2: MultipartBody.Part?,
        @Part file3: MultipartBody.Part?


    ): Call<ResponseBody?>?
}