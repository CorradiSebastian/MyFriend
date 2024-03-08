package com.sebastiancorradi.myfriend.ui.master

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.components.CatCard
import com.sebastiancorradi.myfriend.ui.theme.MyFriendTheme

val TAG = "MasterScreen"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MasterScreen(
    onSeeDetailClicked: (catData: Cat) -> Unit,
    masterViewModel: MasterViewModel = viewModel(),
) {

    //val masterScreenUIState by masterViewModel.masterScreenUIState.collectAsState()
    MyFriendTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            MainContent(onSeeDetailClicked, masterViewModel)
        }
    }
}

fun displayAbout() {
    TODO("Not yet implemented")
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    onSeeDetailClicked: (cat: Cat) -> Unit,
    //onSeeDetailClicked:() -> Unit,
    viewModel: MasterViewModel,
) {
    val state = viewModel.masterScreenUIState.collectAsState()
    val refreshState = rememberPullRefreshState(refreshing = state.value.isLoading, onRefresh = {viewModel.catsRequested()} )
    Log.e(TAG, "cats value: ${state.value.cats}")
    remember {
        viewModel.catsRequested()
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        val (recyclerColumn) = createRefs()

        /*if (recipes?.size == 0) {
            Text("loading")
            viewModel.receipeRequested()
        } else {*/

        LazyColumn(modifier = Modifier
            .constrainAs(recyclerColumn) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .pullRefresh(refreshState)) {
            items(
                state.value.cats
            ) {
                CatCard(onSeeDetailClicked, it)
            }
        }
    }
}