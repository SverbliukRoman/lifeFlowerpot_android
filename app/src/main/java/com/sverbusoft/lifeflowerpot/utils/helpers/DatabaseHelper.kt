package com.sverbusoft.lifeflowerpot.utils.helpers

import com.google.firebase.database.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver

open class DatabaseHelper {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun <T>setValueToBD(value: T, vararg refs: String): Completable{
        var reference: DatabaseReference = database.reference;
        for (ref in refs){
            reference.child(ref)
        }
        return Completable.create {
            reference.setValue(value) { error, _ ->
                if(error!=null){
                    it.onError(error.toException().fillInStackTrace())
                } else {
                    it.onComplete()
                }
            };
        }
    }

    fun setDBListener(vararg refs: String): Observable<DataSnapshot>{
        return Observable.create<DataSnapshot> {
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
    }

    fun getDBData(subscriber: DisposableObserver<DataSnapshot>, vararg refs: String): Observable<DataSnapshot>{
        return Observable.create<DataSnapshot> {
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
    }
}
