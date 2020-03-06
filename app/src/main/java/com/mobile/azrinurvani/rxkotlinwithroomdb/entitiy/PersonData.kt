package com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName=PersonData.TABLE_NAME)
class PersonData (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var userID:Int ?= null,
    @ColumnInfo(name = NAME)
    var nameFUll: String? = null,
    @ColumnInfo(name = AGE)
    var ageTotal: Int? = null
    ){
        companion object{
            const val TABLE_NAME="personal_details"
            const val ID = "id"
            const val NAME ="name"
            const val AGE = "age"
        }
    }
