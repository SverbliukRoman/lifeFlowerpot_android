package com.sverbusoft.lifeflowerpot.dbsource

import android.text.TextUtils
import com.sverbusoft.lifeflowerpot.data.entities.UserData
import com.sverbusoft.lifeflowerpot.utils.constants.DatabaseConstants
import com.sverbusoft.lifeflowerpot.utils.helpers.DatabaseHelper
import io.reactivex.Completable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver
import java.util.*

class UserDBSource: DatabaseHelper() {

    fun createUserData(data: UserData): Completable{
        return setValueToBD(data, DatabaseConstants.USER_DATA, DatabaseConstants.UID!!)
    }

    fun getUserData(){

    }

    fun updateUserData(){

    }

    fun getUserFriens(){

    }
}