package com.example.apicallingvolley.pro2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apicallingvolley.databinding.ActivitySecondBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SecondActivity : AppCompatActivity() {
    lateinit var secondBinding: ActivitySecondBinding

    lateinit var mRequest:RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        intitView()
    }

    private fun intitView() {
        mRequest= Volley.newRequestQueue(this)

        var req=JsonArrayRequest(Request.Method.GET,"https://jsonplaceholder.typicode.com/todos",null,{response->
            var dataList1:ArrayList<SecondResponseItem>
            val listType:Type?=object :TypeToken<List<SecondResponseItem?>?>(){}.type
            dataList1=Gson().fromJson(response.toString(),listType)
            for (i in 0 until dataList1.size){
                Log.e("response", "intitView: "+dataList1.get(i).userId)
                Log.e("response", "intitView: "+dataList1.get(i).id)
                Log.e("response", "intitView: "+dataList1.get(i).title)
                Log.e("response", "intitView: "+dataList1.get(i).completed)
            }
            var secondAdapter=SecondAdapterClass(this,dataList1)
            var manger=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            secondBinding.rcvApi2.layoutManager=manger
            secondBinding.rcvApi2.adapter=secondAdapter

        },{error->
            Log.e("Error", "intitView: "+error.message )
        })
        mRequest.add(req)
    }
}