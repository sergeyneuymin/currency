package com.neuymin.currencies.common

import com.neuymin.currencies.`interface`.RetrofitServices
import com.neuymin.currencies.retrofit.RetrofitClient

object Common {
        private val BASE_URL = "https://cbr.ru/scripts/"
        val retrofitService: RetrofitServices
                get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
