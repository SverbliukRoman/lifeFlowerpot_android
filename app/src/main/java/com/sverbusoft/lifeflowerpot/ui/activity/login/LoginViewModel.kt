package com.sverbusoft.lifeflowerpot.ui.activity.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.managers.UserManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class LoginViewModel : ViewModel() {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    var showToast: MutableLiveData<String> = MutableLiveData()

    fun login() {
        showProgressBar.postValue(true)
        UserManager.newInstace().login(email.toString(), password.toString(), object: Observer<FirebaseUser>{
            override fun onComplete() {
                showProgressBar.postValue(false);
                showToast.postValue("Login Success")
                Log.d(this.javaClass.name, "Login success")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: FirebaseUser) {

            }

            override fun onError(e: Throwable) {
                showProgressBar.postValue(false)
                showToast.postValue(e.message)
                Log.d(this.javaClass.name, "Login failed")
            }

        })


    }

}