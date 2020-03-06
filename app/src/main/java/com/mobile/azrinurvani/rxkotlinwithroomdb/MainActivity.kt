package com.mobile.azrinurvani.rxkotlinwithroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.azrinurvani.rxkotlinwithroomdb.adapter.PersonDataAdapter
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.PersonData
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.db.PersonalDetailsDataBase
import com.mobile.azrinurvani.rxkotlinwithroomdb.view_model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null
    private var personAdapter: PersonDataAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        var databaseInstance = PersonalDetailsDataBase.getDatabaseInstance(this)
        viewModel?.setInstanceOfDb(databaseInstance)

        initViews()
        setListeners()
        observeViewModel()




    }

    private fun observeViewModel() {
        viewModel?.personList?.observe(this, Observer {
            if(!it.isNullOrEmpty()){
                handleData(it)
            }else{
                handleZeroCase()
            }
        })
    }

    private fun handleData(data: List<PersonData>?) {
        rvSavedRecords.visibility = View.VISIBLE
        personAdapter?.setData(data!!)
    }

    private fun handleZeroCase() {
        rvSavedRecords.visibility = View.GONE
        Toast.makeText(this,"No Records Found ",Toast.LENGTH_LONG).show()
    }

    private fun setListeners() {
        saveBtn.setOnClickListener {
            saveData()
        }
        retrieveBtn.setOnClickListener {
            viewModel?.getPersonData()
        }
    }

    private fun saveData() {
        var name = edtName.text.trim().toString()
        var age = edtAge.text.trim().toString()
        edtName.setText("")
        edtAge.setText("")

        if(name.isNullOrBlank()|| age.isNullOrBlank()){
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_LONG).show()
        }else{
            val person = PersonData(nameFUll = name,ageTotal = age.toInt())
            viewModel?.saveDataIntoDb(person)
        }
    }

    private fun initViews() {
       rvSavedRecords.layoutManager = LinearLayoutManager(this)
       personAdapter = PersonDataAdapter(){
           it.let {
               viewModel?.deletePerson(it)
           }
       }
       rvSavedRecords.adapter = personAdapter
    }


}
