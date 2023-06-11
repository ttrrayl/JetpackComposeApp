package com.example.jetpackcomposeapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.MainActivity
import com.example.jetpackcomposeapp.data.OriginoteList
import com.example.jetpackcomposeapp.di.Injection
import com.example.jetpackcomposeapp.ui.State
import com.example.jetpackcomposeapp.ui.ViewModelFactory
import com.example.jetpackcomposeapp.ui.item.OriginoteItems
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.state.collectAsState(initial = State.Loading).value.let { 
        state ->  when (state){
            is State.Loading -> {
                viewModel.getAllData()
            }
            is State.Success -> {
                HomeItem(
                    originoteList = state.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail)
            }
//            is State.Error -> {
//            }
        }
    }
}

@Composable
fun HomeItem(
    originoteList: List<OriginoteList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp) , 
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(originoteList) { data ->
            OriginoteItems(
                image = data.originote.photoUrl,
                price = data.originote.price,
                title = data.originote.name,
                modifier = Modifier.clickable {
                    navigateToDetail(data.originote.id)
                })
        }
    }
}