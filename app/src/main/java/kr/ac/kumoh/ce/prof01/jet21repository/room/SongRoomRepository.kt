package kr.ac.kumoh.ce.prof01.jet21repository.room

import android.content.Context
import androidx.room.Room
import kr.ac.kumoh.ce.prof01.jet21repository.Song
import kr.ac.kumoh.ce.prof01.jet21repository.SongRepository

object SongRoomRepository : SongRepository {
    private lateinit var dao: SongDao
    override fun initRepository(context: Context) {
        dao = Room.databaseBuilder(
            context,
            SongDatabase::class.java,
            "song.db"
        ).build().songDao()
    }

    override suspend fun select(): List<Song> {
        if (!::dao.isInitialized)
            return emptyList()

        val list = mutableListOf<Song>()
        for (song in dao.select()) {
            list.add(Song(song.id.toString(), song.title, song.singer))
        }
        return list
    }

    override suspend fun insert(title: String, singer: String) {
        if (!::dao.isInitialized)
            return

        dao.insert(kr.ac.kumoh.ce.prof01.jet21repository.room.Song(
            0, title, singer)
        )
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }
}