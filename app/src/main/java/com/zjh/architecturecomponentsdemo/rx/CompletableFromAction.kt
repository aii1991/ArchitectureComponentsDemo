package com.zjh.architecturecomponentsdemo.rx;

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.Action

/**
 * @author zjh
 * 2017/8/17
 */
class CompletableFromAction(val run: Action) : Completable() {

    companion object {
        @JvmStatic
        fun create(run: Action): CompletableFromAction{
            return CompletableFromAction(run)
        }
    }

    override fun subscribeActual(s: CompletableObserver?) {
        val d: Disposable = Disposables.empty()
        s?.onSubscribe(d)
        try {
            run.run()
        }catch (e: Throwable){
            Exceptions.throwIfFatal(e)
            if (!d.isDisposed){
                s?.onError(e)
            }
            return
        }
        if (!d.isDisposed){
            s?.onComplete()
        }
    }
}