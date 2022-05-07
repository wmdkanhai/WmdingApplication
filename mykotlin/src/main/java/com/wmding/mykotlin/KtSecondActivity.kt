package com.wmding.mykotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_kt_second.*

class KtSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_second)

        initView()
    }

    private fun initView() {
        val stringExtra = intent.getStringExtra("data")
        textView.text = stringExtra
    }


    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data", "From KtSecondActivity")
        setResult(RESULT_OK, intent)
        finish()
    }
}