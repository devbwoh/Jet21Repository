package kr.ac.kumoh.ce.prof01.jet21repository.pocketbase

import android.content.Context
import android.util.Log
import kr.ac.kumoh.ce.prof01.jet21repository.Song
import kr.ac.kumoh.ce.prof01.jet21repository.SongRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class SongResponse(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<Song>,
)

interface SongApi {
    @GET("api/collections/song/records?fields=id,title,singer")
    suspend fun getSongs(): SongResponse
}

object SongPocketRepository : SongRepository {
    private const val serverUrl = "https://jetpack-server.pockethost.io/"
    private lateinit var songApi: SongApi

    override fun initRepository(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl(serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        songApi = retrofit.create(SongApi::class.java)
    }

    override suspend fun select(): List<Song> {
        lateinit var response: SongResponse

        try {
            response = songApi.getSongs()
            //Log.i("SongPocketRepository::select()", response.toString())
        } catch (e: Exception) {
            Log.e("Error SongPocketRepository::select()", e.toString())
            return emptyList()
        }

        return response.items
    }

    override suspend fun insert(title: String, singer: String) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }
}