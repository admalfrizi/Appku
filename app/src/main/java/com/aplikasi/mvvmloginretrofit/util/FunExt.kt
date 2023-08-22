package com.aplikasi.mvvmloginretrofit.util

fun Int?.int(): Int {
    return this ?: 0
}

fun profileNameInitials(sessionManager: SessionManager): String? {
    val nameInitials = sessionManager.getUser()?.name

    return nameInitials
        ?.split(' ')
        ?.mapNotNull { it.firstOrNull()?.toString() }
        ?.reduce { acc, s -> acc + s }
}
