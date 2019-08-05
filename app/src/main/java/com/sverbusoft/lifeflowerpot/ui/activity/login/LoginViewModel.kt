package com.sverbusoft.lifeflowerpot.ui.activity.login

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.managers.UserManager
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class LoginViewModel : ViewModel() {

    val TAG: String by lazy {
        this::class.java.name
    }

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    var showToast: MutableLiveData<String> = MutableLiveData()
    var startActivity = MutableLiveData<Pair<Class<*>, Bundle?>>()

    fun login() {
        showProgressBar.postValue(true)
        Log.d(TAG, "Start login user by email: ${email.value}, password: ${password.value}")
        UserManager.newInstace()
            .login(email.value.toString(), password.value.toString(), object : SingleObserver<FirebaseUser> {
                override fun onSuccess(t: FirebaseUser) {
                    showProgressBar.postValue(false);
                    showToast.postValue("Login Success")
                    //startActivity.postValue(Pair())
                    Log.d(TAG, "Login success")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    showProgressBar.postValue(false)
                    showToast.postValue(e.message)
                    Log.d(TAG, "Login failed")
                }

            })


    }

}