package com.sverbusoft.lifeflowerpot.ui.activity.signUp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.managers.UserManager
import com.sverbusoft.lifeflowerpot.ui.activity.login.LoginViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class SignUpViewModel: ViewModel() {
    val TAG: String = SignUpViewModel::class.java.name

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    var showToast: MutableLiveData<String> = MutableLiveData()

    fun signUp(){
        showProgressBar.postValue(true)
        Log.d(TAG, "Start sign up user by email: ${email.value}, password: ${password.value}")
        UserManager.newInstace().signUp(email.value.toString(), password.value.toString(), object : Observer<FirebaseUser> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: FirebaseUser) {

            }

            override fun onError(e: Throwable) {

            }

        })
    }
}