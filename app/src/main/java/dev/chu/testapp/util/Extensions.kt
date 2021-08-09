package dev.chu.testapp.util

import android.content.Context
import android.widget.Toast

val Any.TAG get() = this::class.java.simpleName ?: this.toString()

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()