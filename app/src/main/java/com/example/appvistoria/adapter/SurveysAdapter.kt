package com.example.appvistoria.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appvistoria.R
import com.example.appvistoria.data.Survey
import kotlinx.android.synthetic.main.item_list_survey.view.*

class SurveysAdapter(
    private val surveys: List<Survey>
) : RecyclerView.Adapter<SurveysAdapter.SurveyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SurveyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_survey, parent, false)
        return SurveyViewHolder(view)
    }

    override fun getItemCount() = surveys.count()

    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        holder.bindView(surveys[position])
    }

    class SurveyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val license_plate = itemView.tv_license_plate
        private val brand = itemView.tv_brand
        private val data_insert = itemView.tv_data_insert
        private val status = itemView.btn_status

        fun bindView(survey: Survey) {
            license_plate.text = survey.licence_plate
            brand.text = survey.brand
            data_insert.text = survey.data_insert
            when (survey.status) {
                "Aprovada" -> status.setBackgroundResource(R.color.aprovada)
                "Pendente" -> status.setBackgroundResource(R.color.pendente)
                "Reprovada" -> status.setBackgroundResource(R.color.reprovada)
            }
        }
    }
}