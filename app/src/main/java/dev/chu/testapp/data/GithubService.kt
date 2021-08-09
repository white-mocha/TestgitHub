package dev.chu.testapp.data

import dev.chu.testapp.entity.Repository
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("search/repositories?q=stars:>1&sort=stars&per_page=100")
    fun getPopularRepos(): Call<Repository>
}