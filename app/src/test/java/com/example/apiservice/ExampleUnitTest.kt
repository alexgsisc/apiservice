package com.example.apiservice

import com.example.apiservice.user.view.fragment.UserFragment
import org.junit.Test


import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var list1: List<String> = listOf("1", "2", "3", "4")
    var list2: List<String> = listOf("1", "2", "3", "4")

    @Test
    fun addition_isCorrect() {
        assertEquals(list1, listOf("1", "2", "3", "4"))

    }
}