package com.sverbusoft.lifeflowerpot.ui.activity.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.di.component.DaggerLoginModelComponent
import com.sverbusoft.lifeflowerpot.ui.activity.main.MainActivity
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

    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    var showToast: MutableLiveData<String> = MutableLiveData()
    var startActivity = MutableLiveData<Pair<Class<*>, Bundle?>>()

    fun login() {
        showProgressBar.postValue(true)
        Log.d(TAG, "Start login user by email: ${email.value}, password: ${password.value}")
        model.login(
            email.value.toString(),
            password.value.toString(),
            object : SingleObserver<FirebaseUser> {
                override fun onSuccess(t: FirebaseUser) {
                    showProgressBar.postValue(false);
                    showToast.postValue("Login Success")
                    startActivity.postValue(Pair(MainActivity::class.java, null))
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

    fun saveEmail(email: String) {
        SharedPrefHelper.setUserData(email)
    }

    fun onCreate(savedInstanceState: Bundle?) {
        if (!TextUtils.isEmpty(SharedPrefHelper.getPreviousUserData()))
            email.value = SharedPrefHelper.getPreviousUserData()
    }

    fun checkUserLogin() {
        if (FirebaseAuth.getInstance().currentUser != null)
            startActivity.postValue(Pair(MainActivity::class.java, null))
    }
}