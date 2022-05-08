package com.wmding.mykotlin

import android.content.Context
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


    /**
     * 对外暴露
     */
    companion object {

        /**
         * 对外暴露一个启动该页面并传递参数的方法
         */
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, KtSecondActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }
}