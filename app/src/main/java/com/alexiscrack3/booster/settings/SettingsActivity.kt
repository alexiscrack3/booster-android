package com.alexiscrack3.booster.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.R

class SettingsActivity : BoosterActivity() {
    companion object {
        fun getIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        showFragment(R.id.settings_container, SettingsFragment(), false)
    }
}
