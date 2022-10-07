package com.neuymin.currencies.`interface`


import com.neuymin.currencies.model.ValCurs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("XML_dynamic.asp")
    fun getCurrencyList(@Query("date_req1") dateReg1:String, @Query("date_req2") dateReg2:String,
                        @Query("VAL_NM_RQ") valNmRq:String): Call<ValCurs>

}