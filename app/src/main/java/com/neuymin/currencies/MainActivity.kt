package com.neuymin.currencies


import android.os.Bundle
import android.view.View.VISIBLE
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView

import com.neuymin.currencies.`interface`.RetrofitServices
import com.neuymin.currencies.adapter.CurrencyAdapter
import com.neuymin.currencies.common.Common

import com.neuymin.currencies.model.Record
import com.neuymin.currencies.model.ValCurs
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var recyclerView: RecyclerView
    lateinit var inputVal: EditText
    lateinit var adapter: CurrencyAdapter
    lateinit var btnRefresh: ImageView
    lateinit var mProgressBar: ProgressBar
    private var mListItems = listOf<Record>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.currency_recycler_view)
        inputVal = findViewById(R.id.input_val)
        btnRefresh = findViewById(R.id.btn_refresh)
       // mProgressBar = findViewById(R.id.progressBar)
        mService = Common.retrofitService

    }

    override fun onResume() {
        super.onResume()
        btnRefresh.setOnClickListener {

            adapter = CurrencyAdapter()
            recyclerView.adapter = adapter

            val sdf = SimpleDateFormat("dd/MM/yyyy")
            var da = Date()
            val currentDate = sdf.format(da)

            val cal = Calendar.getInstance()
            cal.time = da
            cal.add(Calendar.MONTH, -1)
            da = cal.time
            val monthAgo = sdf.format(da)

            lifecycleScope.launch {
                val v = inputVal.text.toString().toInt()
                getValuteCourse(v,currentDate,monthAgo)
            }
        }
    }

    private fun getValuteCourse(inputVal:Int,currentDate:String,monthAgo:String) {

        mService.getCurrencyList(monthAgo, currentDate,"R01235").enqueue(object : Callback<ValCurs> {
            override fun onFailure(call: Call<ValCurs>, t: Throwable) {
                Toast.makeText(baseContext,t.message.toString(),Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ValCurs>, response: Response<ValCurs>) {
                mListItems = response.body()?.record!!
                mListItems.forEach{
                    if(it.value.replace(",",".").toFloat()>inputVal.toFloat()) {
                        Toast.makeText(baseContext,"Курс доллара от "+ it.date+ " выше!!!",Toast.LENGTH_SHORT).show()
                    }
                    adapter.update(it)
                }
            }
        })
    }
}