package com.mobile.azrinurvani.rxkotlinwithroomdb.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.PersonData
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.db.PersonalDetailsDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private var dataBaseInstance : PersonalDetailsDataBase? = null

    val personList = MutableLiveData<List<PersonData>>()

    fun setInstanceOfDb(dataBaseInstance : PersonalDetailsDataBase){
        this.dataBaseInstance = dataBaseInstance
    }

    fun saveDataIntoDb(data: PersonData){

        dataBaseInstance?.personDataDao()?.insertPersonData(data)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {

                },{

                }
            )?.let {
                compositeDisposable.add(it)
            }
    }

    fun getPersonData(){
        dataBaseInstance?.personDataDao()?.getAllRecords()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                if(!it.isNullOrEmpty()){
                    personList.postValue(it)
                }else{
                    personList.postValue(listOf())
                }
                it?.forEach{
                    Log.v("Person Name ",it.nameFUll)
                }

            },{

            })?.let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()

    }

    fun deletePerson(person: PersonData){
        dataBaseInstance?.personDataDao()?.detelePersonData(person)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {

                },{

                }
            )?.let {
                compositeDisposable.add(it)
            }
    }
}