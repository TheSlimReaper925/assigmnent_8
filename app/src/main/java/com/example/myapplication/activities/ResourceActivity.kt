package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.adapters.ResourceRecyclerAdapter.Companion.RESOURCE_ID
import com.example.myapplication.api.RestClient
import com.example.myapplication.api.dto.ReqResData
import com.example.myapplication.api.dto.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResourceActivity : AppCompatActivity() {
    private lateinit var nameView: TextView
    private lateinit var colorView: TextView
    private lateinit var pantoneValueView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        nameView = findViewById(R.id.textView)
        colorView = findViewById(R.id.textView4)
        pantoneValueView = findViewById(R.id.textView5)

        val resourceId = intent.extras?.getLong(RESOURCE_ID, -1)
        if (resourceId != -1L){
            RestClient.reqResApi.getResource(resourceId!!).enqueue(object : Callback<ReqResData<Resource>>{
                override fun onResponse(
                    call: Call<ReqResData<Resource>>,
                    response: Response<ReqResData<Resource>>
                ) {
                    if (response.isSuccessful){
                        response.body()?.data?.let {
                            nameView.text = it.name
                            colorView.text = it.color
                            pantoneValueView.text = it.pantoneValue
                        }
                    }
                }

                override fun onFailure(call: Call<ReqResData<Resource>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}