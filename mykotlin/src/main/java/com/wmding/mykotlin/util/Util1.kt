package com.wmding.mykotlin.util

import com.wmding.commonlib.utils.MyLog

/**
 * @author wmding
 * @date   5/8/22 5:07 PM
 * @describe static方法
 * companion object 内部会创建一个半生类，
 */
class Util1 {

    companion object {
        fun test1(){
            MyLog.info("test1")
        }


        fun test2(){
            MyLog.info("test2")
        }
    }
}