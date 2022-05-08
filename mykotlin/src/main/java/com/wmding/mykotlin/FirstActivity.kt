package com.wmding.mykotlin

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.wmding.commonlib.utils.MyLog
import com.wmding.mykotlin.util.Util1
import com.wmding.mykotlin.util.test1
import kotlinx.android.synthetic.main.activity_kt_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_first)

        initView()

        initData()
    }

    private fun initData() {

        Util1.test1()

        Util1.test2()

        // Util4中定义的顶层方法，可直接调用
        test1()
    }

    private fun initView() {
        button1.setOnClickListener {
            showToast("1233333")
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
            getDataFromActivity()
        }

        button5.setOnClickListener {
            showAlertDialog3()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("this is dialog")
        builder.setMessage("this is msg")
        builder.setCancelable(false)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            MyLog.info("ok")
        })

        builder.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which ->
            MyLog.info("cancel")
        })

        builder.show()
    }


    private fun showAlertDialog2() {
        AlertDialog.Builder(this).apply {
            setTitle("this is dialog")
            setMessage("this is msg")
            setCancelable(false)
            setPositiveButton("OK") { dialog, which ->
                MyLog.info("ok")
                MyLog.info("which:${which}")
            }

            setNegativeButton("cancel") { dialog, which ->
                MyLog.info("cancel")
                MyLog.info("which:${which}")
            }

            setNeutralButton("hhh") { dialog, which ->
                MyLog.info("hhh")
                MyLog.info("which:${which}")
            }

            show()
        }
    }

    /**
     * 男女选择对话框
     */
    private fun showAlertDialog3() {
        val item = arrayOf("男", "女")
        var currentItem: Int = 0
        AlertDialog.Builder(this).apply {
            setTitle("this is dialog")
            setCancelable(false)
            setSingleChoiceItems(item, 0) { dialog, which ->
                currentItem = which
                MyLog.info(item[which])
            }
            setPositiveButton("OK") { dialog, which ->
                MyLog.info("ok")
                MyLog.info("which:${which}")

                MyLog.info("选中的是${item[currentItem]}")
            }

            show()
        }

    }


    private fun sendData2Activity() {
        val intent = Intent(this, KtSecondActivity::class.java)
        intent.putExtra("data", "FirstActivity data")
        startActivity(intent)

//        KtSecondActivity.actionStart(this,"111","222")
    }


    private fun getDataFromActivity() {
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