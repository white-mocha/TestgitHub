package dev.chu.testapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.chu.testapp.R
import dev.chu.testapp.data.GithubApi
import dev.chu.testapp.databinding.ActivityMainBinding
import dev.chu.testapp.entity.Repository
import dev.chu.testapp.util.TAG
import dev.chu.testapp.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        GithubApi.service.getPopularRepos().enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse isSuccessful data = ${response.body()}")
                    toast("onResponse isSuccessful data = ${response.body()}")
                }
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                Log.e(TAG, "onFailure")
            }
        })
    }
}