package com.sverbusoft.lifeflowerpot.utils.constants

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object DatabaseConstants {
    val UID: String? = FirebaseAuth.getInstance().currentUser?.uid;
    const val USER_DATA = "users"
}