package com.sverbusoft.lifeflowerpot.dbsource

import android.text.TextUtils
import com.sverbusoft.lifeflowerpot.data.entities.UserData
import com.sverbusoft.lifeflowerpot.utils.constants.DatabaseConstants
import com.sverbusoft.lifeflowerpot.utils.helpers.DatabaseHelper

class UserDBSource: DatabaseHelper() {

    fun createUserData(data: UserData){
        if(!TextUtils.isEmpty(DatabaseConstants.UID))
        setValueToBD(data, DatabaseConstants.USER_DATA, DatabaseConstants.UID!!)
    }

    fun getUserData(){

    }

    fun updateUserData(){

    }

    fun getUserFriens(){

    }
}