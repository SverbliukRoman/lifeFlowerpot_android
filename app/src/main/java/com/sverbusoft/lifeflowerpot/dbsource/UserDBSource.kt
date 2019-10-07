package com.sverbusoft.lifeflowerpot.dbsource

import android.text.TextUtils
import com.sverbusoft.lifeflowerpot.data.entities.UserData
import com.sverbusoft.lifeflowerpot.utils.constants.DatabaseConstants
import com.sverbusoft.lifeflowerpot.utils.helpers.DatabaseHelper
import io.reactivex.observers.DisposableCompletableObserver

class UserDBSource: DatabaseHelper() {

    fun createUserData(data: UserData, subscriber: DisposableCompletableObserver){
        if(!TextUtils.isEmpty(DatabaseConstants.UID))
        setValueToBD(data, subscriber, DatabaseConstants.USER_DATA, DatabaseConstants.UID!!)
    }

    fun getUserData(){

    }

    fun updateUserData(){

    }

    fun getUserFriens(){

    }
}