package com.example.appvistoria.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SurveyBodyResponse(
    //val surveyResults: List<SurveyResultsResponse>
    @Json(name="license_plate")
    val license_plate: String,
//    @Json(name="year")
//    val year: String,
    @Json(name="brand")
    val brand: String,
//    @Json(name="type")
//    val type: String,
//    @Json(name="color")
//    val color: String,
//    @Json(name="kind")
//    val kind: String,
//    @Json(name="uf")
//    val uf: String,
//    @Json(name="chassis")
//    val chassis: String,
//    @Json(name="engine")
//    val engine: String,
//    @Json(name="chassis_photo")
//    val chassis_photo: String?,
//    @Json(name="chassis_obs")
//    val chassis_obs: String?,
//    @Json(name="engine_photo")
//    val engine_photo: String?,
//    @Json(name="engine_obs")
//    val engine_obs: String?,
//    @Json(name="front_photo")
//    val front_photo: String?,
//    @Json(name="front_obs")
//    val front_obs: String?,
//    @Json(name="back_photo")
//    val back_photo: String?,
//    @Json(name="back_obs")
//    val back_obs: String?,
//    @Json(name="hodometer_photo")
//    val hodometer_photo: String?,
//    @Json(name="hodometer_obs")
//    val hodometer_obs: String?,
//    @Json(name="survey_place")
//    val survey_place: String,
    @Json(name = "status")
    val status: String,
    @Json(name="data_insert")
    val data_insert: String
)

//    @Json(name="license_plate")
//    val license_plate: String,
//    @Json(name="brand")
//    val brand: String,
//    @Json(name="status")
//    val status: String,
//    @Json(name="data_insert")
//    val data_insert: String
//)

