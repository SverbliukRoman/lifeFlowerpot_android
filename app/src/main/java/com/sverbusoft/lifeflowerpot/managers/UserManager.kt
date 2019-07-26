package com.sverbusoft.lifeflowerpot.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.*

class UserManager {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        fun newInstace() = UserManager()
    }

    public fun login(email: String, password: String, subscriber: SingleObserver<FirebaseUser>) =
        Single.create { emitter: SingleEmitter<FirebaseUser> ->
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    emitter.onSuccess(FirebaseAuth.getInstance().currentUser!!)
                } else {
                    emitter.onError(task.exception!!.fillInStackTrace())
                }

            }
        }.subscribe(subscriber)

    public fun signUp(email: String, password: String, subscriber: SingleObserver<FirebaseUser>) = Single.create<FirebaseUser> {
        emitter ->
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                emitter.onSuccess(FirebaseAuth.getInstance().currentUser!!)
            } else {
                emitter.onError(it.exception!!.fillInStackTrace())
            }
        }
    }.subscribe(subscriber)

}