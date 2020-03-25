package com.sotcha.apagoreushsms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sotcha.apagoreushsms.model.User
import com.sotcha.apagoreushsms.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        if (fragment is MainFragment) {
            fragment.listener = mainFragmentListener
        }
    }

    private val mainFragmentListener = object : MainFragment.MainFragmentListener {
        override fun goToReasons(user: User) {
            startActivity(Intent(this@MainActivity, ReasonActivity::class.java))
        }
    }

}
