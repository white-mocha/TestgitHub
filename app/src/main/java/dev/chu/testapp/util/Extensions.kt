package dev.chu.testapp.util

val Any.TAG get() = this::class.java.simpleName ?: this.toString()