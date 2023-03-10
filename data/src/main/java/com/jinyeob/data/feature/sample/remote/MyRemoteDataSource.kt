package com.jinyeob.data.feature.sample.remote

import retrofit2.http.GET

internal interface MyRemoteDataSource {
    @GET("/sample")
    suspend fun getMyData(): Result<MyResponseDto>
}
