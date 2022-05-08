package com.wmding.mykotlin.util

import com.wmding.commonlib.utils.MyLog

/**
 * @author wmding
 * @date   5/8/22 5:14 PM
 * @describe @JvmStatic注解
 *
 * 使用@JvmStatic注解来创建static方法
 * 该注解只能用在 单例类（object） 和 伴生类（companion object） 中
 */
class Util3 {

    companion object {

        @JvmStatic
        fun test1(){
            MyLog.info("test1")
        }
    }

}