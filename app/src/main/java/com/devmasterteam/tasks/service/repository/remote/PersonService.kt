package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.PersonModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {
    @POST("Authentication/Login")
    @FormUrlEncoded
    fun login(
        @Field("email")email: String,
        @Field("password") password: String
    ):Call<PersonModel>

    @POST("Authentication/Create")
    fun create (
        @Field("name") name:String,
        @Field("email")email: String,
        @Field("password") password: String
    ):Call<PersonModel>

}
/*

{
    "name": "Douglas",
    "token": "100QjqCaOsXj+hWI5BI9CdoOJY39L5fpAxTWTPlmdUQa0Lb4etrPkA==",
    "personKey": "eQFdbwBLyUDMZM+clu/toFp4ecmUtf8484iEYeECFVU="
}

 */