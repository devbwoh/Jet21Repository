package kr.ac.kumoh.ce.prof01.jet21repository

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.ce.prof01.jet21repository.ui.theme.Jet21RepositoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jet21RepositoryTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SongList()
    }
}

@Composable
fun SongList() {
    val viewModel: SongViewModel = viewModel()
    val songs by viewModel.songs.collectAsState()

    LazyVerticalStaggeredGrid(
        StaggeredGridCells.Fixed(2),
    ) {
        items(
            items = songs,
            key = {
                it.id
            }
        ) {
            SongItem(it)
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            SongTitle(song.title)
            SingerName(song.singer)
        }
    }
}

@Composable
fun SongTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        lineHeight = 25.sp,
    )
}

@Composable
fun SingerName(name: String) {
    Text(name)
}