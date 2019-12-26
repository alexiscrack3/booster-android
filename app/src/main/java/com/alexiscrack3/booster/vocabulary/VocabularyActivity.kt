package com.alexiscrack3.booster.vocabulary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.R

class VocabularyActivity : BoosterActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, VocabularyActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
    }

    override fun onStart() {
        super.onStart()
        showFragment(VocabularyFragment(), false)
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        showFragment(R.id.vocabulary_container, fragment, addToBackStack)
    }
}
