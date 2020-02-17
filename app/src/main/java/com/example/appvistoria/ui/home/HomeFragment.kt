package com.example.appvistoria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appvistoria.R
import com.example.appvistoria.adapter.SurveysAdapter
import com.example.appvistoria.data.Survey
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        root.recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        root.recyclerView?.adapter = SurveysAdapter(getSurveys())


//        with(recyclerView) {
//            layoutManager = LinearLayoutManager(this@HomeFragment), RecyclerView.VERTICAL, false)
//            setHasFixedSize(true)
//            adapter = SurveysAdapter(getSurveys())
//        }
//        val emptyView: ImageView = root.findViewById(R.id.no_item_list)
//        val list: ListView = root.findViewById(R.id.recyclerView)
//        list.setEmptyView(emptyView)


        homeViewModel.text.observe(this, Observer {
            //textView.text = it
        })
        return root
    }

    fun getSurveys(): List<Survey> {
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
            )

        )
    }
}