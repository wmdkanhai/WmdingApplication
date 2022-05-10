package com.wmding.fragmentlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wmding.fragmentlib.fragment.FirstFragment
import com.wmding.fragmentlib.fragment.SecondFragment
import kotlinx.android.synthetic.main.activity_fr_main.*

class FrMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fr_main)

        initView()
    }

    private fun initView() {
        changeFragment(FirstFragment())
        initBottomNavigationView()
    }

    /**
     * 切换fragment
     */
    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        // 开启一个事务
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_group, fragment)

        // 如果要将所有的fragment放入栈中时，addToBackStack(null)
        //transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * todo
     *
     */
    private fun initBottomNavigationView() {
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_main_pager -> changeFragment(FirstFragment())
                R.id.tab_knowledge_hierarchy -> changeFragment(FirstFragment())
                R.id.tab_wx_article -> changeFragment(SecondFragment())
                R.id.tab_navigation -> changeFragment(FirstFragment())
                R.id.tab_project -> changeFragment(SecondFragment())
                else -> {
                }
            }
            true
        }
    }

}