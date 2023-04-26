package com.example.retrofitapi_integration

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.retrofitapi_integration.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //https://meme-api.com/gimme
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.btnNewMeme.setOnClickListener {
            getData()
        }
    }

    private fun getData(){

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        // After typing enqueue() enter ctrl+shift+space
        retrofitInstance.ApiInterface.getData().enqueue(object : Callback<responseDataClass?> {
            override fun onResponse(
                call: Call<responseDataClass?>,
                response: Response<responseDataClass?>
            ) {
                binding.memeTitle.text = response.body()?.title
                binding.memeAuthor.text = response.body()?.author
                Glide.with(this@MainActivity).load(response.body()?.url).into(binding.memeImage)
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}