package com.example.appvistoria.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appvistoria.R
import com.example.appvistoria.data.Survey
import kotlinx.android.synthetic.main.item_list_survey.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SurveysAdapter(

    private val surveys: List<Survey>,
    val onItemClickListener: ((survey: Survey) -> Unit)// criaçao de lambda
) : RecyclerView.Adapter<SurveysAdapter.SurveyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_survey, parent, false)
        return SurveyViewHolder(view, onItemClickListener)
    }

    override fun getItemCount() = surveys.count()

    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        holder.bindView(surveys[position])
    }

    class SurveyViewHolder(
        itemView: View,
        private val onItemClickListener: ((survey: Survey) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {
//        private val license = itemView.tv_license_plate
//        private val brand = itemView.tv_brand
//        private val data = itemView.tv_data_insert

        private var license = itemView.tv_license_plate
        private var brand = itemView.tv_brand
        private var status = itemView.btn_status
        private var data = itemView.tv_data_insert

        fun bindView(survey: Survey) {

//            val string = "2017-07-25"
//            val date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE)
//            val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
//            val date = LocalDate.parse(survey.data_insert, formatter)

            //Log.i("dddd", ""+date)
//            val formato = "dd/MM/yyyy"
//            val format = SimpleDateFormat(formato)
//            val dataFormatada = format.format(survey.data_insert)


            license.text = survey.license_plate
            brand.text = survey.brand

            Log.i("Return", survey.license_plate)

            when (survey.status) {
                "Aprovada" -> status.setBackgroundResource(R.color.aprovada)
                "Pendente" -> status.setBackgroundResource(R.color.pendente)
                "Reprovada" -> status.setBackgroundResource(R.color.reprovada)
            }
            data.text = survey.data_insert

            itemView.setOnClickListener {
                onItemClickListener.invoke(survey)

            }

        }
    }
}