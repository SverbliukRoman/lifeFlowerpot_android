package com.sverbusoft.lifeflowerpot.ui.activity.signUp

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.di.component.DaggerLoginModelComponent
import com.sverbusoft.lifeflowerpot.ui.activity.login.LoginActivity
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SignUpViewModel : ViewModel() {
    private val model = DaggerLoginModelComponent.builder().build().getModel()

    val TAG: String by lazy {
        this::class.java.name
    }

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    val startActivity = MutableLiveData<Pair<Class<*>, Bundle?>>()
    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    var showToast: MutableLiveData<String> = MutableLiveData()

    fun signUp() {
        showProgressBar.postValue(true)
        Log.d(TAG, "Start sign up user by email: ${email.value}, password: ${password.value}")
        model.signUp(
            email.value.toString(),
            password.value.toString(),
            object : SingleObserver<FirebaseUser> {
                override fun onSuccess(t: FirebaseUser) {
                    showProgressBar.postValue(false)
                    showToast.postValue("Sign Up Success")
                    startActivity.postValue(Pair(LoginActivity::class.java, null))
                    Log.d(TAG, "Sign Up success")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    showProgressBar.postValue(false)
                    showToast.postValue(e.message)
                    Log.d(TAG, "Sign Up failed")
                }

            })
    }
}