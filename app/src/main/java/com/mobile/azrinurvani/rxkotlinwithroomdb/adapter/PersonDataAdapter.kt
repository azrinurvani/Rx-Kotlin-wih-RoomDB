package com.mobile.azrinurvani.rxkotlinwithroomdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.azrinurvani.rxkotlinwithroomdb.R
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.PersonData
import kotlinx.android.synthetic.main.item_person.view.*

class PersonDataAdapter(private val onDeleteClick: (PersonData)-> Unit):
    RecyclerView.Adapter<PersonDataAdapter.DataViewHolder>() {

    private var personDataList = mutableListOf<PersonData>()

    fun setData(list: List<PersonData>){
        personDataList.clear()
        personDataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_person,parent,false)
        ){
            onDeleteClick.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return personDataList.size ?: 0
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.setData(personDataList[position])
    }

    inner class DataViewHolder(itemView: View, val onDeleteClick: (PersonData) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun setData(personData: PersonData){
            itemView.apply {
                txtName.text = "Name : ${personData.nameFUll}"
                txtAge.text = "Age : ${personData.ageTotal.toString()}"
                imDelete.setOnClickListener {
                    onDeleteClick(personData)
                }
            }
        }
    }

}