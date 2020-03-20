package com.example.appvistoria.repository

import android.app.Application
import com.example.appvistoria.dao.SurveyDao
import com.example.appvistoria.dao.SurveyDatabase
import com.example.appvistoria.data.Survey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SurveyRepository(application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var surveyDao: SurveyDao?

    init {
        val db = SurveyDatabase.getDatabase(application)
        surveyDao = db?.surveyDao()
    }

    fun getSurveysLocal() = surveyDao?.getSurveys()

    fun setSurveyLocal(survey: Survey) {
        launch  { setSurveyBG(survey) }
    }

    private suspend fun setSurveyBG(survey: Survey){
        withContext(Dispatchers.IO){
            surveyDao?.setSurvey(survey)
        }
    }

}