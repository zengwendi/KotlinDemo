package com.weiclicai.kotlindemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

/**
 * Created by zengwendi on 2017/11/2.
 */
inline fun <reified T> T.log(log: Any) {
    Log.e(T::class.simpleName, log.toString())
}

inline fun Context.toast(c: CharSequence) {
    Toast.makeText(this, c, Toast.LENGTH_SHORT).show()
}

inline fun <reified T> Activity.startActivity(bundle: Bundle = Bundle()) {
    var intent = Intent(this, T::class.java);
    intent.putExtras(bundle)
    this.startActivity(android.content.Intent())
}