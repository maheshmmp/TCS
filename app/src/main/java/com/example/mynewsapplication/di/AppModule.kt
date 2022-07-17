package com.example.mynewsapplication.di

import android.annotation.SuppressLint
import android.content.Context
import com.example.mynewsapplication.app.MyNewsApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@SuppressLint("StaticFieldLeak")
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    var context: Context = MyNewsApp.applicationContext()

    @Provides
    fun context(): Context{
        return context
    }

}