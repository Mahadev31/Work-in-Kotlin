package com.example.apicallingvolley.pro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apicallingvolley.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var mRequestQueue: RequestQueue
    lateinit var adapter: AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        mRequestQueue = Volley.newRequestQueue(this)

        var reg = JsonArrayRequest(Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts", null, { response ->
                var dataList: ArrayList<ResponseItem>
                val listType: Type? = object : TypeToken<List<ResponseItem?>?>() {}.type
                dataList = Gson().fromJson(response.toString(), listType)
                for (i in 0 until dataList.size) {
                    Log.e("response", "initView: " + dataList.get(i).id)
                    Log.e("response", "initView: " + dataList.get(i).userId)
                    Log.e("response", "initView: " + dataList.get(i).title)
                    Log.e("response", "initView: " + dataList.get(i).body + "\n\n")



                }
                adapter = AdapterClass(dataList)
                var manger=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                binding. rcvApi.layoutManager=manger
                binding. rcvApi.adapter=adapter
            }, { error ->
                Log.e("error", "initView: " + error.message)
            }
        )
        mRequestQueue.add(reg)


    }

}