package com.example.apiservice.user.view

import com.example.apiservice.common.entity.TypeDataView

interface UserView {
    fun setPaintData(data: List<TypeDataView>)

    fun loadView()

    fun formView()
}