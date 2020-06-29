package dev.ktown.dailydoge.repositories.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShibeOnlineApi {
    /**
     * By default only ever take one image at at time.
     */
    @GET("api/shibes")
    suspend fun getShibes(
        @Query("count") count: Int = 1,
        @Query("urls") useUrls: String = "true",
        @Query("httpsUrls") useHttpsUrls: String = "true"
    ): Response<ShibeOnlineResponse>
}