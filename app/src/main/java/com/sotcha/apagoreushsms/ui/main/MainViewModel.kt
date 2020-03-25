package com.sotcha.apagoreushsms.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.sotcha.apagoreushsms.model.Reason
import com.sotcha.apagoreushsms.model.SharedPreferencesService
import com.sotcha.apagoreushsms.model.User

class MainViewModel : ViewModel() {
    val reasons: List<Reason> = Reason.LIST
    var user = User("", "")


    fun load(context: Context) {
        user = SharedPreferencesService.readUser(context)

    }


    fun save(context: Context) {
        SharedPreferencesService.saveUser(context, user)
    }


}
