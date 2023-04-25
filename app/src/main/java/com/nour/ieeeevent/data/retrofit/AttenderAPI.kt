package com.nour.ieeeevent.data.retrofit

import com.nour.ieeeevent.data.modeles.AttenderDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface AttenderAPI {

    @GET
    suspend fun getAttenders(@Url path: String, @Query("sheetNumber") sheetNumber: String): Response<AttenderDto>

    @POST
    suspend fun upDateAttenderBackgroundColor(@Url path: String, @Query("sheetNumber") sheetNumber: String, @Query("cell") cell: String)
}