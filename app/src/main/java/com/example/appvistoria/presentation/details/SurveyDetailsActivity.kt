package com.example.appvistoria.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.appvistoria.R
import com.example.appvistoria.data.Survey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_survey_details.*
import kotlinx.android.synthetic.main.fragment_image_dialog.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SurveyDetailsActivity : AppCompatActivity() {
    lateinit var survey: Survey

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_details)

        survey = intent.getParcelableExtra("EXTRA_SURVEY")
        val license_plate = survey.license_plate
        val year: String = survey.year
        val brand: String = survey.brand
        val type: String = survey.type
        val color: String = survey.color
        val kind: String = survey.kind
        var uf: String = survey.uf
        val chassis: String = survey.chassis
        var engine: String = survey.engine
        val chassis_photo: String? = survey.chassis_photo
        val chassis_obs: String? = survey.chassis_obs
        val engine_photo: String? = survey.engine_photo
        val engine_obs: String? = survey.engine_obs
//        val front_photo: String? = survey.front_photo
//        val front_obs: String? = survey.front_obs
        val back_photo: String? = survey.back_photo
        val back_obs: String? = survey.back_obs
        val odometer_photo: String? = survey.odometer_photo
        val odometer_obs: String? = survey.odometer_obs
        val survey_place: String = survey.survey_place
        val status: String = survey.status
        val data_insert: String = survey.data_insert

        Log.i("radio", survey.survey_place)
//        radioParqueamento.isClickable = false
//       radioParqueamento.isActivated=false
//        radioDetran.isClickable = false
//        radioOutro.isClickable = false

        when (survey.survey_place) {
            "Parqueamento" -> radioParqueamento.isChecked = true.apply {
                radioGroup[1].isEnabled = false
                radioGroup[2].isEnabled = false
                edt_outro_local.visibility = View.INVISIBLE
            }

            "Detran-Sede" -> radioDetran.isChecked = true.apply {
                radioGroup[0].isEnabled = false
                radioGroup[2].isEnabled = false
                edt_outro_local.visibility = View.INVISIBLE

            }

            else -> { // Note the block
                radioOutro.isChecked = true
                radioGroup[0].isEnabled = false
                radioGroup[1].isEnabled = false

                edt_outro_local.setText(survey_place)
            }
        }

        edt_placa.setText(license_plate)
        edt_ano.setText(year)
        edt_marca.setText(brand)
        edt_tipo.setText(type)
        edt_cor.setText(color)
        edt_especie.setText(kind)
        edt_uf.setText(uf)
        edt_chassi.setText(chassis)
        edt_motor.setText(engine)

        //img_chassi_veiculo.setText(chassis_photo)
        edt_obs_chassi.setText(chassis_obs)

        Picasso.get()
            .load("http://10.11.1.233:3333/" + chassis_photo)
            //.load(chassis_photo)
            .resize(300, 250)
            .centerCrop()
            .placeholder(R.drawable.ic_add_a_photo_24px)
            //.error(R.drawable.user_placeholder_error)
            .into(img_chassi_veiculo)

        edt_obs_motor.setText(engine_obs)
        Picasso.get()
            .load("http://10.11.1.233:3333/" + engine_photo)
            //.load(engine_photo)
            .resize(300, 250)
            .centerCrop()
            .placeholder(R.drawable.ic_add_a_photo_24px)
            //.error(R.drawable.user_placeholder_error)
            .into(img_motor_veiculo)

//        edt_obs_frente.setText(front_obs)
//        Picasso.get()
//            .load("http://10.11.3.205:3333/" + front_photo)
//            .resize(300, 250)
//            .centerCrop()
//            .placeholder(R.drawable.ic_add_a_photo_24px)
//            //.error(R.drawable.user_placeholder_error)
//            .into(img_frente_veiculo)

        edt_obs_traseira.setText(back_obs)
        Picasso.get()
            .load("http://10.11.1.233:3333/" + back_photo)
            //.load(back_photo)
            .resize(300, 250)
            .centerCrop()
            .placeholder(R.drawable.ic_add_a_photo_24px)
            //.error(R.drawable.user_placeholder_error)
            .into(img_traseira_veiculo)

        edt_obs_odometro.setText(odometer_obs)
        Picasso.get()
            .load("http://10.11.1.233:3333/" + odometer_photo)
            //.load(odometer_photo)
            .resize(300, 250)
            .centerCrop()
            .placeholder(R.drawable.ic_add_a_photo_24px)
            //.error(R.drawable.user_placeholder_error)
            .into(img_odometro_veiculo)

//        img_chassi_veiculo.setOnClickListener {
//            openAlbum(img_motor_veiculo)
//        }
//        img_motor_veiculo.setOnClickListener {
//            openAlbum(img_motor_veiculo)
//        }
//        img_traseira_veiculo.setOnClickListener {
//            openAlbum(img_traseira_veiculo)
//        }
//        img_odometro_veiculo.setOnClickListener {
//            openAlbum(img_odometro_veiculo)
//        }

    }

    fun openAlbum(view: View) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = ImageDialogFragment()
        val bundle = Bundle()

        /*
         * Obtendo o posicionamento da imagem acionada, pois é ela
         * que inicialmente será carregada no ImageDialogFragment.
         * */
        val imagePosition = when (view.id) {
            R.id.img_chassi_veiculo ->0
            R.id.img_motor_veiculo -> 1
            R.id.img_traseira_veiculo -> 2
            R.id.img_odometro_veiculo -> 3
            else -> 0
        }
        Log.i("aaaaa", survey.toString())

        bundle.putParcelable("survey", survey)
        Log.i("log----------", survey.chassis_photo)
        bundle.putInt("position", imagePosition)

        fragment.arguments = bundle
        fragment.show(transaction, ImageDialogFragment.KEY)
    }

    companion object {
        fun getStartIntent(context: Context, survey: Survey): Intent {
            return Intent(context, SurveyDetailsActivity::class.java).apply {
                this.putExtra("EXTRA_SURVEY", survey)
            }
        }
    }
}
