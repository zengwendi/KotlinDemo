package com.goat.kotlinextend


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.weiclicai.kotlindemo.User
import com.weiclicai.kotlindemo.log
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(private val context: Context, val name: String, private val default: T) : ReadWriteProperty<Any?, T> {

    private val prefs: SharedPreferences by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun <U> findPreference(name: String, default: U): U = with(prefs) {
        log("取值$default")
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        log("赋值$value")
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            is User -> putString(name, serialObject(value))
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }

    private fun serialObject(o: Any): String {
        try {
            val baos = ByteArrayOutputStream()
            val oos = ObjectOutputStream(baos)
            oos.writeObject(o)
            oos.flush()
            val buf = baos.toByteArray()
            val str = getSerialString(buf)
            oos.close()
            baos.close()
            return str
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    private fun getSerialString(buf: ByteArray): String {
        val sb = StringBuffer()
        for (b in buf) {
            sb.append(b.toInt())
            sb.append(",")
        }
        return sb.toString()
    }

    fun unSerialObject(serialString: String): Any? {
        try {
            val inputStream = ByteArrayInputStream(getSerialBytes(serialString))
            val ois = ObjectInputStream(inputStream)
            val o = ois.readObject()
            ois.close()
            inputStream.close()
            return o
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getSerialBytes(str: String): ByteArray {
        val strs = str.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var list=str.split(",".toRegex()).dropLastWhile {
            println(it)
            it.isEmpty()
        }
        for (s in list) {
            Log.d("TAG",s)
        }
        val buf = ByteArray(strs.size)
        for (i in strs.indices) {
            buf[i] = java.lang.Byte.parseByte(strs[i])
        }
        return buf
    }
}