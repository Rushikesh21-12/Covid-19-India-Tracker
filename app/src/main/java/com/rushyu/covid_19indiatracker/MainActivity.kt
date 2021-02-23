package com.rushyu.covid_19indiatracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.AbsListView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var listAdapter: ListAdapter
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header,list,false))
        fetchResults()
    }

    private fun fetchResults() {
        GlobalScope.launch{
            val response = withContext(Dispatchers.IO){ Client.api.execute() }

            if(response.isSuccessful){
                val data= Gson().fromJson(response.body?.string(),Response::class.java)
                launch(Dispatchers.Main){
                    bindCombinedData(data.statewise[0])
                    bindStateWiseData(data.statewise.subList(0,data.statewise.size))
                }
            }

        }
    }

    private fun bindStateWiseData(subList: List<StatewiseItem>){

        listAdapter= ListAdapter(subList)
        list.adapter = listAdapter

    }

    private fun bindCombinedData(data:StatewiseItem)
    {
        confirmedTv.text=data.confirmed
        activeTv.text=data.active
        recoveredTv.text=data.recovered
        deceasedTv.text=data.deaths

    }
}