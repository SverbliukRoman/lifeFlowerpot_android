package com.sverbusoft.lifeflowerpot.ui.activity.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.sverbusoft.lifeflowerpot.managers.UserManager
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class SignUpViewModel: ViewModel() {
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    public fun signUp(email: String, password: String){
        UserManager.newInstace().signUp(email, password, object : Observer<FirebaseUser> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: FirebaseUser) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}