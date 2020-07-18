package com.example.apiservice.user.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.apiservice.user.model.ModelData
import com.example.apiservice.user.presenter.interceptor.UserInterceptorImp
import com.example.apiservice.user.view.UserView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UserPresenter(val view: UserView, val context: Context) {

    val userInteractor = UserInterceptorImp(context)

    fun getPersonSerie(page: Int) {

        userInteractor.getListUser(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.e(":::::::", "ABC") }
            .subscribe {
                it.info?.next?.split("=")?.get(1)?.toInt()?.let { it1 ->
                    view.setPaintData(
                        it.results,
                        it1
                    )
                }

            }


    }

}

