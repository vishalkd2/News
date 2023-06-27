package com.example.news

import retrofit2.Call
import retrofit2.http.GET

interface NewsApiServices {
    @GET("v2/top-headlines?country=in&apiKey=dff447207b6341c6a27b5e02bca2f8ee")
    fun getData():Call<NewsDataClass>
}