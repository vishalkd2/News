package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        adapter = NewsAdapter(emptyList()) { newsUrl ->
            openWebViewActivity(newsUrl)
        }
        recyclerview.adapter = adapter
        fetchNewsData() // Fetch news data from JSON API
    }

    private fun fetchNewsData() {
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<NewsDataClass?> {
            override fun onResponse(
                call: Call<NewsDataClass?>?,
                response: Response<NewsDataClass?>?
            ) {
                if (response?.isSuccessful == true) {
                    val newsData = response.body()
                    if (newsData != null) {
                        adapter.updateData(newsData.articles) // Assuming "articles" is the list of news items in the NewsDataClass

                        // Optionally, you can also perform any other operations with the newsData object
                    }
                } else {
                    // Handle unsuccessful response (e.g., show an error message)
                    Toast.makeText(this@MainActivity, "Failed to fetch news data", Toast.LENGTH_SHORT).show()
                }            }

            override fun onFailure(call: Call<NewsDataClass?>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "${t?.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun openWebViewActivity(newsUrl: String) {
        val intent = Intent(this, WebNewsActivity::class.java)
        intent.putExtra("news_url", newsUrl)
        startActivity(intent)
    }
}