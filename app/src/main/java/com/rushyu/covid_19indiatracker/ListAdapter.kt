package com.rushyu.covid_19indiatracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(val list: List<StatewiseItem>): BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view=p1?:LayoutInflater.from(p2?.context)
            .inflate(R.layout.item_list,p2,false)
            val item=list[p0]
            view.confirmedTv.text=item.confirmed
            view.activeTv.text=item.active
            view.recoveredTv.text=item.recovered
            view.deceasedTv.text=item.deaths
            view.stateTv.text=item.state
            return  view
    }
    override fun getCount(): Int=list.size

    override fun getItem(p0: Int)=list[p0]

    override fun getItemId(p0: Int)=p0.toLong()


}