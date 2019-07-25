package com.sverbusoft.lifeflowerpot.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer

class UserManager {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        fun newInstace() = UserManager()
    }

    public fun login(email: String, password: String, subscriber: Observer<FirebaseUser>) =
        Observable.create { emitter: ObservableEmitter<FirebaseUser> ->
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    emitter.onNext(FirebaseAuth.getInstance().currentUser!!)
                    emitter.onComplete()
                } else {
                    emitter.onError(task.exception!!.fillInStackTrace())
                }

            }
        }.subscribe(subscriber)

    public fun signUp(email: String, password: String, subscriber: Observer<FirebaseUser>) = Observable.create<FirebaseUser> {
        emitter ->
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                emitter.onNext(FirebaseAuth.getInstance().currentUser!!)
                emitter.onComplete()
            } else {
                emitter.onError(it.exception!!.fillInStackTrace())
            }
        }
    }.subscribe(subscriber)

}