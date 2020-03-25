package com.sotcha.apagoreushsms

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.sotcha.apagoreushsms.ui.main.ReasonFragment


class ReasonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.visibility = View.VISIBLE

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Μήνυμα στο ${Constants.SMS_NUMBER}"

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ReasonFragment.newInstance(), FRAGMENT_TAG)
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)

        menu?.findItem(R.id.action_info)?.apply {
            icon?.let { icon ->
                DrawableCompat.setTint(
                    icon, ContextCompat.getColor(this@ReasonActivity, android.R.color.white)
                )
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_info -> {
                toggleInfo()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun toggleInfo() {
        val f = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as ReasonFragment
        f.toggleInfo()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        if (fragment is ReasonFragment) {
            fragment.listener = reasonFragmentListener
        }
    }

    private val reasonFragmentListener = object : ReasonFragment.ReasonFragmentListener {
        override fun onSendSms(text: String) {
            val smsIntent = Intent(Intent.ACTION_SENDTO)
            smsIntent.data = Uri.parse("smsto: ${Constants.SMS_NUMBER}")
            smsIntent.putExtra("sms_body", text)
            startActivity(smsIntent)
            finishAffinity()
        }

    }


    companion object {
        private const val FRAGMENT_TAG = "FRAGMENT_TAG"
    }
}
