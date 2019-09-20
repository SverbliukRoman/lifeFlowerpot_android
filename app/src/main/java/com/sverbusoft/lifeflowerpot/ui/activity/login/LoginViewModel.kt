package com.sverbusoft.lifeflowerpot.ui.activity.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.di.component.DaggerLoginModelComponent
import com.sverbusoft.lifeflowerpot.utils.helpers.SharedPrefHelper
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class LoginViewModel : ViewModel() {
    private val model = DaggerLoginModelComponent.builder().build().getModel()

    val TAG: String by lazy {
        this::class.java.name
    }

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var successLogin: MutableLiveData<FirebaseUser> = MutableLiveData()
    var errorLogin: MutableLiveData<String> = MutableLiveData()

    fun login() {
        isLoading.postValue(true)
        Log.d(TAG, "Start login user by email: ${email.value}, password: ${password.value}")
        model.login(
            email.value.toString(),
            password.value.toString(),
            object : SingleObserver<FirebaseUser> {
                override fun onSuccess(t: FirebaseUser) {
                    isLoading.postValue(false);
                    successLogin.postValue(t)
                    Log.d(TAG, "Login success")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    isLoading.postValue(false)
                    errorLogin.postValue(e.message)
                    Log.d(TAG, "Login failed")
                }

            })
    }

    fun saveEmail(email: String) {
        SharedPrefHelper.setUserData(email)
    }

    fun onCreate(savedInstanceState: Bundle?) {
        if (!TextUtils.isEmpty(SharedPrefHelper.getPreviousUserData()))
            email.value = SharedPrefHelper.getPreviousUserData()
    }

    fun checkUserLogin() {
        if(FirebaseAuth.getInstance().currentUser != null){
            successLogin.postValue(FirebaseAuth.getInstance().currentUser)
        }
    }
}