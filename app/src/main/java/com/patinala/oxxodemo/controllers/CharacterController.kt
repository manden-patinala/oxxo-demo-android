package com.patinala.oxxodemo.controllers

import com.patinala.oxxodemo.common.Constants
import com.patinala.oxxodemo.common.Global
import com.patinala.oxxodemo.models.Character
import com.patinala.oxxodemo.models.CharacterDataWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

class CharacterController {

    interface CharacterControllerApi {
        @GET("/v1/public/characters")
        fun getAllCharacters(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String): Call<CharacterDataWrapper>
    }

    companion object {

        var characterControllerApi: CharacterControllerApi? = null

        @Volatile private var instance: CharacterController ? = null
        fun  getInstance(): CharacterController {
            return instance?: synchronized(this){
                CharacterController().also {
                    characterControllerApi = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(CharacterControllerApi::class.java)

                    instance = it
                }
            }
        }
    }

    fun getAllCharacters(complete: (result: List<Character>) -> Unit, failure: (error: Any?, message: String) -> Unit) {
        characterControllerApi!!.getAllCharacters(Constants.API_KEY, Global.getCurrentTimeMillis(), Global.getHash()).enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                //println(response.body())
                complete(response.body()!!.data.results)
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                println(t.message)
            }
        })
    }

}