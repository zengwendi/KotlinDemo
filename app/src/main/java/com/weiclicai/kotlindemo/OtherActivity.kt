package com.weiclicai.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.goat.kotlinextend.Preference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_other.*

/**
 * Created by zengwendi on 2017/11/2.
 */
class OtherActivity : AppCompatActivity() {
    private var string:String by Preference<String>(this,"aInt","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        textView.text = "OtherActivity"
        string=edit.text.toString()
        read.setOnClickListener({
            log(string)
        })
    }
}


