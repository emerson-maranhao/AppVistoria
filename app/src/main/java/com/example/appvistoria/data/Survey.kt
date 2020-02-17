package com.example.appvistoria.data

data class Survey(
    var licence_plate: String = "",
    var year: String = "",
    var brand: String = "",
    var type: String = "",
    var color: String = "",
    var kind: String = "",
    var uf: String = "",
    var chassis: String = "",
    var engine: String = "",
    var chassis_photo: String? = "",
    var chassis_obs: String? = "",
    var engine_photo: String? = "",
    var engine_obs: String? = "",
    var front_photo: String? = "",
    var front_obs: String? = "",
    var back_photo: String? = "",
    var back_obs: String? = "",
    var hodometer_photo: String? = "",
    var hodometer_obs: String? = "",
    var survey_place: String = "",
    var status: String = "",
    var data_insert: String

)