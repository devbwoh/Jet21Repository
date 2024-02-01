package kr.ac.kumoh.ce.prof01.jet21repository

import android.content.Context

data class Song(
    val id: String,
    val title: String,
    val singer: String,
)

interface SongRepository {
    fun initRepository(context: Context): Unit
    suspend fun select(): List<Song>
    suspend fun insert(title: String, singer: String): Unit
    suspend fun delete(id: String): Unit
}