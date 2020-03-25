package com.sotcha.apagoreushsms

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sotcha.apagoreushsms.model.User
import com.sotcha.apagoreushsms.ui.main.MainFragment
import com.sotcha.apagoreushsms.ui.main.ReasonFragment


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
        if (fragment is ReasonFragment) {
            fragment.listener = reasonFragmentListener
        }

    }

    private val mainFragmentListener = object : MainFragment.MainFragmentListener {
        override fun goToReasons(user: User) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ReasonFragment.newInstance())
                .commitNow()
        }
    }
    private val reasonFragmentListener = object : ReasonFragment.ReasonFragmentListener {
        override fun onSendSms(text: String) {
            val smsIntent = Intent(Intent.ACTION_SENDTO)
            smsIntent.data = Uri.parse("smsto: ${Constants.SMS_NUMBER}")
            smsIntent.putExtra("sms_body", text)
            startActivity(smsIntent)
            finish()
        }

    }

}
