package com.wmding.servicelib

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ServiceMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_main)
    }

    fun btnServiceTest1(view: View) {
        val intent = Intent(this, ServiceTest1Activity::class.java)
        startActivity(intent)
    }
}