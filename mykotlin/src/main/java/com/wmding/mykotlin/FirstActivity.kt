package com.wmding.mykotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.wmding.commonlib.utils.MyLog
import kotlinx.android.synthetic.main.activity_kt_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_first)

        initView()
    }

    private fun initView() {
        button1.setOnClickListener {
            showToast("hahha")
        }

        button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")
            startActivity(intent)
        }

        button3.setOnClickListener {
            sendData2Activity()
        }

        button4.setOnClickListener {
            getDataFromAcivity()
        }
    }

    private fun sendData2Activity() {
        val intent = Intent(this, KtSecondActivity::class.java)
        intent.putExtra("data", "FirstActivity data")
        startActivity(intent)

    }


    private fun getDataFromAcivity() {
        val intent = Intent(this, KtSecondActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val stringExtra = data?.getStringExtra("data")
                MyLog.info("stringExtra:$stringExtra")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 调用了父类的getMenuInflater()方法
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> addItem()
            R.id.remove_item -> removeItem()
        }
        return true
    }


    private fun addItem() {
        showToast("addItem")
    }

    private fun removeItem() {
        showToast("removeItem")
    }


    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
}