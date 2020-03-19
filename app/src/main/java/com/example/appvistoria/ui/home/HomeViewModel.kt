package com.example.appvistoria.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appvistoria.dao.SurveyDatabase
import com.example.appvistoria.data.ApiService
import com.example.appvistoria.data.Survey
import com.example.appvistoria.repository.SurveyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: SurveyRepository = SurveyRepository(application)


    fun setSurvey(survey: Survey) {
        repository.setSurveyLocal(survey)
        Log.i("setSurvey", repository.toString())
    }

    //this is the data that we will fetch asynchronously
    val surveyLiveData: MutableLiveData<List<Survey>> = MutableLiveData()
    val surveyLiveData2: MutableLiveData<Survey> = MutableLiveData()
    val liveData:LiveData<List<Survey>>
    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val surveyDao = SurveyDatabase.getDatabase(application)?.surveyDao()
        repository = SurveyRepository(application)
        liveData = repository.getSurveysLocal()!!
    }
    fun getSurveysLocal() {
        //liveData.value = repository.getSurveysLocal()
    }

    //we will call this method to get the data
    fun getSurveys() { //if the list is null
        ApiService.service.getSurveys().enqueue(
            object : Callback<List<Survey>> {

                override fun onResponse(
                    call: Call<List<Survey>?>,
                    response: Response<List<Survey>?>
                ) {
                    if (response.isSuccessful) {

                        val surveys: MutableList<Survey> = mutableListOf()


                        response.body()?.let {
                            val s: List<Survey> = it
                            Log.i("ok", "ok------------------------->" + surveyLiveData)

                            for (i in s.indices) {
                                setSurvey(s[i])

                                Log.i("for", "#######################" + s[i])
                            }
                            surveyLiveData.value = s
                        }
                    }
                }

                override fun onFailure(call: Call<List<Survey>>, t: Throwable) {
                    Log.i("erro", "erro------------------------->" + t)
                    // surveyLiveData.value= getSurveysLocal()?.value
                    // Log.i("livedata:--------------",surveyLiveData.toString())

                }

            })

    }


    //fun insertSurvey(survey: Survey , call: Callback<Survey>) {
    fun insertSurvey(survey: Survey) {
        Log.i("entrou", "insert survey")
        ApiService.service.insertSurvey(survey).enqueue(

            object : Callback<Survey?> {
                override fun onResponse(call: Call<Survey?>, response: Response<Survey?>) {
                    Log.i("onresponse", "method response")
                    if (response.isSuccessful) {

                        response?.body()?.let {
                            val note: Survey = it
                            surveyLiveData2.value = note
                            Log.i("it-----------------", it.toString())
                            Log.i("result-----------------", response.code().toString())
                        }
                    } else {
                        Log.i("else----------------", response.toString())

                    }
                }

                override fun onFailure(call: Call<Survey?>, t: Throwable) {
                    Log.i("errr-----------------", t.message)

                    Log.e("onFailure error", t?.message)
                }
            })
    }
}
//class HomeViewModel : ViewModel() {
//
//
//    //    val surveyLiveData = MutableLiveData<List<Survey>>().apply {
////        value = createFakeSurveys()
////
////    }
//    val surveyLiveData: MutableLiveData<List<Survey>> = MutableLiveData()
//
//
//
//    fun getSurvey() {
//        surveyLiveData.value = createFakeSurveys()
//
//    }
//
//
//    fun createFakeSurveys(): List<Survey> {
//        return listOf(
//            return listOf(
//                Survey(
//                    "JXS0855",
//                    "2007/2008",
//                    "Fox",
//                    "Automóvel",
//                    "Vermelha",
//                    "Passageiro",
//                    "AM",
//                    "9BWDAS53IOP005683",
//                    "CCRAAFO165",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "10000",
//                    "Parqueamento",
//                    "Aprovada",
//                    "2020-01-30"
//                ),
//                Survey(
//                    "PHF3760",
//                    "2014",
//                    "FOD Ká",
//                    "Automóvel",
//                    "Branca",
//                    "Passageiro",
//                    "AM",
//                    "9BWDAS53434OFRO44",
//                    "CCRAAFO3FE5",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "10000",
//                    "Detran-Sede",
//                    "Pendente",
//                    "2020-02-01"
//                ),
//                Survey(
//                    "OAO8023",
//                    "2005",
//                    "Sandero",
//                    "Automóvel",
//                    "Cinza",
//                    "Passageiro",
//                    "AM",
//                    "9BWDAS5398PR8753",
//                    "CCRAAE44OIU",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "10000",
//                    "São Francisco",
//                    "Reprovada",
//                    "2020-02-01"
//                ),
//                Survey(
//                    "JXS4152",
//                    "2019",
//                    "Uno",
//                    "Automóvel",
//                    "Cinza",
//                    "Passageiro",
//                    "AM",
//                    "9BWDAS5398PR8753",
//                    "CCRAAE44OIU",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "10000",
//                    "São Francisco",
//                    "Reprovada",
//                    "2020-02-01"
//                ),
//                Survey(
//                    "JXA0522",
//                    "2020",
//                    "Honda City",
//                    "Automóvel",
//                    "Cinza",
//                    "Passageiro",
//                    "AM",
//                    "9BWDAS5398PR8753",
//                    "CCRAAE44OIU",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "TESTE",
//                    "",
//                    "10000",
//                    "São Francisco",
//                    "Aprovada",
//                    "2020-02-03"
//                )
//            )
//        )
//
//
//    }
//}
//
//
