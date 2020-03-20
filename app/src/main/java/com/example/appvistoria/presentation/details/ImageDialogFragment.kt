package com.example.appvistoria.presentation.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.example.appvistoria.R
import com.example.appvistoria.data.Survey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_dialog.*


class ImageDialogFragment : DialogFragment(), View.OnClickListener {
    lateinit var survey: Survey
    var imgs = arrayOf<String>()
    companion object {
        const val KEY = "image_dialog"

        /*
         * Todas as constantes do projeto são importantes, mas as
         * seguintes facilitarão a leitura do código ao invés de
         * apenas utilizar "1" ou "-1".
         * */
        const val COUNT_PLUS = 1
        const val COUNT_LESS = -1
    }

    var imagePosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.i("cccc",survey.toString())

        /*
         * Código que deixa o DialogFragment em modo fullscreen.
         * */
        setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen

        )
//        setStyle(
//            DialogFragment.STYLE_NO_FRAME, R.style.AppTheme
//        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         /*
         * Até o inal da invocação deste método ainda não podemos
         * acessar as Views do layout fragment_image_dialog com a
         * sintaxe permitida pelo plugin kotlin-android-extensions.
         * */
        return inflater.inflate(
            R.layout.fragment_image_dialog,
            container, false
        )
        Log.i("dddd", survey.toString())

    }

    /*
     * Os acessos as Views estão no método onResume(), pois somente
     * depois de onCreateView() é que podemos utilizar a sintaxe
     * do plugin kotlin-android-extensions, sem necessidade de uso
     * do método findViewById().
     * */
    override fun onResume() {
        super.onResume()

        /*
         * Acessando dados enviados de CarAdapter depois do
         * acionamento de algum item em lista.
         * */
        val parcelable = arguments!!.getParcelable<Survey>("survey")
        survey =
            (if (parcelable != null) parcelable else throw NullPointerException("Expression 'arguments!!.getParcelable( \"survey\")' must not be null")) as Survey
        Log.i("cccc", survey.toString())
        imagePosition = arguments!!.getInt("position")

        iv_close.setOnClickListener(this)
        iv_arrow_left.setOnClickListener(this)
        iv_arrow_right.setOnClickListener(this)

        setImage(false)

        /*
         * Ampliando a capacidade de zoom máximo na imagem.
         * */
        iv_image.maximumScale = 5.0F
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_arrow_left -> prevImage()
            R.id.iv_arrow_right -> nextImage()
            R.id.iv_close -> close()
        }
    }

    private fun prevImage() {
        setImage(true, COUNT_LESS)
    }

    private fun nextImage() {
        setImage(true, COUNT_PLUS)
    }

    private fun setImage(applyCount: Boolean, typeCount: Int = COUNT_PLUS) {

        /*
         * Verificação se deve ou não ser aplicado o contador a
         * propriedade imagePosition, pois logo na abertura do
         * DialogFragment não há necessidade de invocar o algoritmo
         * contador, posteriormente, na mudança de imagem em tela, o
         * contador deve ser invocado para o correto cálculo de qual
         * botão passador de imagem poderá ou não ficar em tela.
         * */
        if (applyCount) {
            imagePosition += when (typeCount) {
                COUNT_PLUS -> 1
                else -> -1
            }
        }
        imgs = arrayOf(survey.chassis_photo.toString(),survey.engine_photo.toString(),survey.back_photo.toString(),survey.odometer_photo.toString())
        //imgs[0] = survey.chassis_photo.toString()

        Log.i("ffff", survey.chassis_photo.toString())
        Log.i("position", "" + imagePosition)
        Log.i("lista0", imgs[0])
        Log.i("lista1", imgs[1])
        Log.i("lista2", imgs[2])
        Log.i("lista3", imgs[3])



//        imgs[1] = survey.engine_photo.toString()
//        imgs[2] = survey.back_photo.toString()
//        imgs[3] = survey.hodometer_photo.toString()
        Picasso
            .get()
            .load("http://10.11.1.233:3333/" +imgs[imagePosition])
            .into(iv_image)

        verifyButtons()
    }

    /*
     * Verificação se os botões passadores de imagem podem ou não
     * permanecer em tela de acordo com a posição atual da imagem
     * em exibição.
     * */
    private fun verifyButtons() {
        iv_arrow_left.visibility =
            if (imagePosition == 0)
                View.GONE
            else
                View.VISIBLE

        iv_arrow_right.visibility =
            if (imagePosition == imgs.size - 1)
                View.GONE
            else
                View.VISIBLE
    }

    private fun close() {
        dismiss()
    }
}