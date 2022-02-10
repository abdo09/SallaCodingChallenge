package com.coding.challenge.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.coding.challenge.data.DB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val dataModule = module {

    // DB database instance
    single {
        Room.databaseBuilder(androidApplication(), DB::class.java,
            "_data.db")
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    single { get<DB>().brandDataDAO() }
    single { get<DB>().productDetailsDAO() }

    //get shared preferences
    single {
        getSharedPrefs(get())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(get()).edit()
    }

}


fun getSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences("default", Context.MODE_PRIVATE)
}


