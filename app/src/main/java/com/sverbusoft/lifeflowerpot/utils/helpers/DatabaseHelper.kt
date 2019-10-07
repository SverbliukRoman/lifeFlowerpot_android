package com.sverbusoft.lifeflowerpot.utils.helpers

import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

open class DatabaseHelper {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable();

    fun <T>setValueToBD(value: T, vararg refs: String){
        var reference: DatabaseReference = database.reference;
        for (ref in refs){
            reference.child(ref)
        }
        reference.setValue(value);
    }

    fun setDBListener(subscriber: DisposableObserver<DataSnapshot>, vararg refs: String): Disposable{
        var o = Observable.create<DataSnapshot> {
            var reference = database.reference;

            for (ref in refs){
                reference.child(ref)
            }

            reference.addValueEventListener(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    it.onError(p0.toException().fillInStackTrace())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    it.onNext(p0)
                }

            })
        }

        var disposable = o.subscribeWith(subscriber)
        compositeDisposable.add(disposable)
        return disposable;
    }

    fun getDBData(subscriber: DisposableObserver<DataSnapshot>, vararg refs: String): Disposable{
        var o = Observable.create<DataSnapshot> {
            var reference = database.reference;

            for (ref in refs){
                reference.child(ref)
            }

            reference.addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    it.onError(p0.toException().fillInStackTrace())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    it.onNext(p0)
                }

            })
        }

        var disposable = o.subscribeWith(subscriber)
        compositeDisposable.add(disposable)
        return disposable;
    }
}
