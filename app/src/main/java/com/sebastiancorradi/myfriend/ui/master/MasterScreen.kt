package com.sebastiancorradi.myfriend.ui.master

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.components.CatCard
import com.sebastiancorradi.myfriend.ui.components.WindowInfo
import com.sebastiancorradi.myfriend.ui.components.rememberWindowInfo
import com.sebastiancorradi.myfriend.ui.theme.MyFriendTheme

val TAG = "MasterScreen"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MasterScreen(
    onSeeDetailClicked: (catData: Cat) -> Unit,
    masterViewModel: MasterViewModel = viewModel(),
) {
        // A surface container using the 'background' color from the theme
       // Surface(
       //     modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
       // ) {
            MainContent(onSeeDetailClicked, masterViewModel)
       // }
}

fun displayAbout() {
    TODO("Not yet implemented")
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    onSeeDetailClicked: (cat: Cat) -> Unit,
    viewModel: MasterViewModel,
) {
    val state = viewModel.masterScreenUIState.collectAsState()
    val refreshState = rememberPullRefreshState(refreshing = state.value.isLoading, onRefresh = {viewModel.catsRequested()} )
    rememberSaveable {
        viewModel.catsRequested()
    }

        val windowInfo = rememberWindowInfo()
        if ((windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) || (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)) {
            drawSingleColumn(refreshState, state, onSeeDetailClicked)
        } else {
            drawDoubleColumn(refreshState, state, onSeeDetailClicked)
        }

    //drawSingleColumn(refreshState, state, onSeeDetailClicked)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun drawSingleColumn(refreshState: PullRefreshState, state: State<MasterScreenUIState>, onSeeDetailClicked: (cat: Cat) -> Unit,) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        val (recyclerColumn, pullRefreshIndicator) = createRefs()

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
        PullRefreshIndicator(refreshing = state.value.isLoading, state = refreshState, modifier = Modifier.constrainAs(pullRefreshIndicator){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun drawDoubleColumn(refreshState: PullRefreshState, screenState: State<MasterScreenUIState>, onSeeDetailClicked: (cat: Cat) -> Unit,) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        val (recyclerColumn, pullRefreshIndicator) = createRefs()

        /*-------------------*/

        val state = rememberLazyStaggeredGridState()

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
                .constrainAs(recyclerColumn){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .pullRefresh(refreshState),
            state = state,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            //verticalArrangement = Arrangement.spacedBy(10.dp),
            content = {

                items(
                    screenState.value.cats
                ) {
                    CatCard(onSeeDetailClicked, it)
                }
            }
        )

        /*-------------------*/


        /*LazyColumn(modifier = Modifier
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
        }*/
        PullRefreshIndicator(refreshing = screenState.value.isLoading, state = refreshState, modifier = Modifier.constrainAs(pullRefreshIndicator){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}
