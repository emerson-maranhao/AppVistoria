package com.example.appvistoria.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appvistoria.R
import com.example.appvistoria.presentation.details.SurveyDetailsActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        if (progressBar != null) {
//            Log.i("progress","passou")
//            val visibility =
//                if (progressBar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
//            progressBar.visibility = visibility
//            val handler = Handler()
//
//            handler.postDelayed({
//                progressBar.visibility = View.GONE
//
//
//            }, 2000)
//        }


        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (progressBar != null) {
            Log.i("progress", "passou")
            val visibility =
                if (progressBar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            progressBar.visibility = visibility

            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)


            val handler = Handler()

            handler.postDelayed({


                progressBar.visibility = View.GONE
                if (isConnected) {
                    Log.i("conexão", "Tem internet")
                    homeViewModel.surveyLiveData.observe(this, Observer {
                        //textView.text = it

                        it?.let { surveys ->
                            with(recyclerView) {
                                //progressBar?.visibility = View.GONE

                                recyclerView?.layoutManager =
                                    LinearLayoutManager(
                                        this@HomeFragment.context,
                                        RecyclerView.VERTICAL,
                                        false
                                    )

                                setHasFixedSize(true)
                                adapter = SurveysAdapter(surveys) { survey ->
                                    val intent =
                                        SurveyDetailsActivity.getStartIntent(context, survey)

                                    this@HomeFragment.startActivity(intent)

                                }
                            }
                        }

                    })

                    homeViewModel.getSurveys()

                } else {
                    Log.i("conexão", "Não tem internet")
                    homeViewModel.liveData.observe(this, Observer {
                        //textView.text = it
                        Log.i("raaaaaaaaa", it.toString())

                        it?.let { surveys ->
                            with(recyclerView) {
                                //progressBar?.visibility = View.GONE

                                recyclerView?.layoutManager =
                                    LinearLayoutManager(
                                        this@HomeFragment.context,
                                        RecyclerView.VERTICAL,
                                        false
                                    )

                                setHasFixedSize(true)
                                adapter = SurveysAdapter(surveys) { survey ->
                                    val intent =
                                        SurveyDetailsActivity.getStartIntent(context, survey)

                                    this@HomeFragment.startActivity(intent)
                                }
                            }
                        }
                    })
                    homeViewModel.getSurveysLocal()
                }
            }, 500)
        }


    }

}