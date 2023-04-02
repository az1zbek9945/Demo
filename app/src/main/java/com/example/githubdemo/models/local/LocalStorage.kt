package com.example.githubdemo.models.local

import android.content.Context
import com.example.githubclone.utils.BooleanPreference
import com.example.githubclone.utils.StringPreference
import com.example.githubdemo.app.App

class LocalStorage {

    companion object{
        val pref =
            App.instance.getSharedPreferences("GitHubAppSharedPref", Context.MODE_PRIVATE)
    }

    var username by StringPreference(pref,"temp")

    var code by StringPreference(pref)

    var token by StringPreference(pref)

    var isLogin by BooleanPreference(pref)

}