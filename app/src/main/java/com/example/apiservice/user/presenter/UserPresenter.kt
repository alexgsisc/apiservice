package com.example.apiservice.user.presenter

import android.content.Context
import com.example.apiservice.user.model.ModelData
import com.example.apiservice.user.presenter.interceptor.UserInterceptorImp
import com.example.apiservice.user.view.UserView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UserPresenter(val view: UserView, context: Context) {

    val userInteractor = UserInterceptorImp(context)

    fun getPersonSerie() {

        userInteractor.getListUser(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.setPaintData(it.results)
            }


    }

}

