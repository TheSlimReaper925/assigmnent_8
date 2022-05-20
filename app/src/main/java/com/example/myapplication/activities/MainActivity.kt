package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.ResourceRecyclerAdapter
import com.example.myapplication.api.RestClient
import com.example.myapplication.api.dto.ReqResData
import com.example.myapplication.api.dto.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        RestClient.initClient()
        RestClient.reqResApi.getResources(1).enqueue(object : Callback<ReqResData<List<Resource>>>{
            override fun onResponse(
                call: Call<ReqResData<List<Resource>>>,
                response: Response<ReqResData<List<Resource>>>
            ) {
                if (response.isSuccessful){
                    response.body()?.data?.let {
                        recyclerView.adapter = ResourceRecyclerAdapter(it)
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

            override fun onFailure(call: Call<ReqResData<List<Resource>>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}