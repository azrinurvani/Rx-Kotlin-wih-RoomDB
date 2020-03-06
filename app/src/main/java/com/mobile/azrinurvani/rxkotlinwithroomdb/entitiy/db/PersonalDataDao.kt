package com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.db

import androidx.room.*
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.PersonData
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface PersonalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPersonData(data: PersonData) : Completable

    @Query("SELECT * FROM ${PersonData.TABLE_NAME}")
    fun getAllRecords(): Single<List<PersonData>>

    @Delete
    fun detelePersonData(person: PersonData) : Completable

    @Update
    fun updatePersonData(person: PersonData)

}