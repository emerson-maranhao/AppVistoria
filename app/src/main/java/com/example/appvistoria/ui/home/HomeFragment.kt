package com.example.appvistoria.ui.home

import android.os.Bundle
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


//    private lateinit var homeViewModel: HomeViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        return inflater.inflate(R.layout.fragment_home, container, false)
//        recyclerView.setHasFixedSize(true)
//
//        root.recyclerView?.adapter = SurveysAdapter(surveys)
//        with(recyclerView) {
//            layoutManager = LinearLayoutManager(this@HomeFragment), RecyclerView.VERTICAL, false)
//            setHasFixedSize(true)
//            adapter = SurveysAdapter(getSurveys())
//        }
////        val emptyView: ImageView = root.findViewById(R.id.no_item_list)
////        val list: ListView = root.findViewById(R.id.recyclerView)
////        list.setEmptyView(emptyView)
////
////
////        private lateinit var homeViewModel: HomeViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//
//
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
//
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//
//
//        root.recyclerView?.layoutManager =
//            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//
////        recyclerView.setHasFixedSize(true)
//
//        //root.recyclerView?.adapter = SurveysAdapter(surveys)
////        with(recyclerView) {
////            layoutManager = LinearLayoutManager(this@HomeFragment), RecyclerView.VERTICAL, false)
////            setHasFixedSize(true)
////            adapter = SurveysAdapter(getSurveys())
////        }
////        val emptyView: ImageView = root.findViewById(R.id.no_item_list)
////        val list: ListView = root.findViewById(R.id.recyclerView)
////        list.setEmptyView(emptyView)
//
//
//        homeViewModel.surveyLiveData.observe(this, Observer {
//            //textView.text = it
//            it?.let { surveys ->
//                Log.i("survey", surveys.toString())
//                root.recyclerView?.adapter = SurveysAdapter(surveys)
//
//            }
//        })
//        homeViewModel.getSurveys()
//        return root
//
//
//    }


    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)



        homeViewModel.surveyLiveData.observe(this, Observer {
            //textView.text = it
            it?.let { surveys ->
                with(recyclerView) {

                    recyclerView?.layoutManager =
                        LinearLayoutManager(this@HomeFragment.context, RecyclerView.VERTICAL, false)

                    setHasFixedSize(true)
                    adapter = SurveysAdapter(surveys) { survey ->
                        val intent =SurveyDetailsActivity.getStartIntent(context, survey)
                        this@HomeFragment.startActivity(intent)
                    }
                }
                //recyclerView?.adapter = SurveysAdapter(surveys)
//                recyclerView.addOnScrollListener(
//                    InfiniteScrollListener({ requestNews() }, linearLayout)
//                )

            }
        })

        homeViewModel.getSurveys()
    }

}