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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.appvistoria.R
import com.example.appvistoria.presentation.details.SurveyDetailsActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(){

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

        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            Handler().postDelayed(
                {
                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setRefreshing(true)
                    }
                    // TODO Fetching data from server
                    Log.i("swipe", "onRefresh called from SwipeRefreshLayout")

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
                    swipeRefreshLayout.isRefreshing = false
                },
                2500
            )
        })

        //        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )



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

//    override fun onRefresh() {
//        /**
//         * Showing Swipe Refresh animation on activity create
//         * As animation won't start on onCreate, post runnable is used
//         */
//        swipeRefreshLayout.postDelayed(Runnable {
//            if (swipeRefreshLayout != null) {
//                swipeRefreshLayout.setRefreshing(true)
//            }
//            // TODO Fetching data from server
//            Log.i("swipe", "onRefresh called from SwipeRefreshLayout")
//
//            homeViewModel.surveyLiveData.observe(this, Observer {
//                //textView.text = it
//
//                it?.let { surveys ->
//                    with(recyclerView) {
//                        //progressBar?.visibility = View.GONE
//
//                        recyclerView?.layoutManager =
//                            LinearLayoutManager(
//                                this@HomeFragment.context,
//                                RecyclerView.VERTICAL,
//                                false
//                            )
//
//                        setHasFixedSize(true)
//                        adapter = SurveysAdapter(surveys) { survey ->
//                            val intent =
//                                SurveyDetailsActivity.getStartIntent(context, survey)
//
//                            this@HomeFragment.startActivity(intent)
//
//                        }
//
//                    }
//                }
//
//            })
//
//            homeViewModel.getSurveys()
//            swipeRefreshLayout.isRefreshing = false
//
//        },1000)
//
//    }

}


