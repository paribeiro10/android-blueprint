package com.paribeiro.blueprint.functional.section.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import blueprint.features.onboarding.functional.section.activities.splash.Splash

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: This should evolve to a better pattern!
        startActivity(Intent(this@MainActivity, Splash::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

}
