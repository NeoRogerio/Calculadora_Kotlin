package com.example.myapplication

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class AplicationDb : Application() {
    override fun onCreate() {
        super.onCreate()

        //init Realm
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .name("Calculadora.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(config)
    }
}