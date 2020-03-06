package com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobile.azrinurvani.rxkotlinwithroomdb.entitiy.PersonData


const val DB_VERSION = 1
const val DB_NAME = "PersonDataSample.db"

@Database(entities = [PersonData::class],version = DB_VERSION,exportSchema = false)
abstract class PersonalDetailsDataBase : RoomDatabase() {
    abstract fun personDataDao() : PersonalDataDao

    companion object{
        @Volatile
        private var databaseInstance : PersonalDetailsDataBase? = null

        fun getDatabaseInstance(mContext : Context): PersonalDetailsDataBase =
            databaseInstance ?: synchronized(this){
                databaseInstance ?: buildDatabaseInstance(mContext).also{
                    databaseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext,PersonalDetailsDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}