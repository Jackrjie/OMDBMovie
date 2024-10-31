package com.jackrjie.omdbmovie.presentation.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.jackrjie.omdbmovie.R
import com.jackrjie.omdbmovie.domain.model.Movie


@Composable
fun DashboardScreen(
    movies: LazyPagingItems<Movie>,
    search: (String) -> Unit
) {
    val context = LocalContext.current
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(key1 = movies.loadState) {
        if(movies.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                context.getString(R.string.error_msg) + (movies.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
                search(it.text)
            },
            label = { Text(stringResource(R.string.search_movies)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {

            if (movies.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        movies.itemCount,
                        movies.itemKey { item: Movie -> item.imdbID }) { movie ->
                        val movieItem = movies[movie]

                        if (movieItem != null)
                            MovieItem(
                                movie = movieItem,
                                modifier = Modifier.fillMaxWidth()
                            )
                    }
                    item {
                        if (movies.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}