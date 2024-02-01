package kr.ac.kumoh.ce.prof01.jet21repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.ac.kumoh.ce.prof01.jet21repository.room.SongRoomRepository

class SongViewModel(application: Application) : AndroidViewModel(application) {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>>
        get() = _songs

    private var repository: SongRepository = SongRoomRepository

    init {
        repository.initRepository(application.applicationContext)
        add("소주 한 잔", "임창정")
    }

    fun select() {
        viewModelScope.launch(Dispatchers.IO) {
            _songs.value = repository.select()
        }
    }

    fun add(title: String, singer: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(title, singer)
            _songs.value = repository.select()
        }
    }
}