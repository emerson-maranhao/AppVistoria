package com.example.appvistoria.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appvistoria.data.Survey


class HomeViewModel : ViewModel() {

    //    val surveyLiveData = MutableLiveData<List<Survey>>().apply {
//        value = createFakeSurveys()
//
//    }
    val surveyLiveData: MutableLiveData<List<Survey>> = MutableLiveData()

    fun getSurvey() {
        surveyLiveData.value = createFakeSurveys()
    }

    fun createFakeSurveys(): List<Survey> {
        return listOf(
            return listOf(
                Survey(
                    "JXS0855",
                    "2007/2008",
                    "Fox",
                    "Automóvel",
                    "Vermelha",
                    "Passageiro",
                    "AM",
                    "9BWDAS53IOP005683",
                    "CCRAAFO165",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "10000",
                    "Parqueamento",
                    "Aprovada",
                    "2020-01-30"
                ),
                Survey(
                    "PHF3760",
                    "2014",
                    "FOD Ká",
                    "Automóvel",
                    "Branca",
                    "Passageiro",
                    "AM",
                    "9BWDAS53434OFRO44",
                    "CCRAAFO3FE5",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "10000",
                    "Detran-Sede",
                    "Pendente",
                    "2020-02-01"
                ),
                Survey(
                    "OAO8023",
                    "2005",
                    "Sandero",
                    "Automóvel",
                    "Cinza",
                    "Passageiro",
                    "AM",
                    "9BWDAS5398PR8753",
                    "CCRAAE44OIU",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "10000",
                    "São Francisco",
                    "Reprovada",
                    "2020-02-01"
                ),
                Survey(
                    "JXS4152",
                    "2019",
                    "Uno",
                    "Automóvel",
                    "Cinza",
                    "Passageiro",
                    "AM",
                    "9BWDAS5398PR8753",
                    "CCRAAE44OIU",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "10000",
                    "São Francisco",
                    "Reprovada",
                    "2020-02-01"
                ),
                Survey(
                    "JXA0522",
                    "2020",
                    "Honda City",
                    "Automóvel",
                    "Cinza",
                    "Passageiro",
                    "AM",
                    "9BWDAS5398PR8753",
                    "CCRAAE44OIU",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "TESTE",
                    "",
                    "10000",
                    "São Francisco",
                    "Aprovada",
                    "2020-02-03"
                )
            )
        )


    }
}


