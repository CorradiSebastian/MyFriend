package com.sebastiancorradi.myfriend.ui.master

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun MasterScreen(
    onSeeDetailClicked: (catData: Cat) -> Unit,
    masterViewModel: MasterViewModel = viewModel(),
) {

    val masterScreenUIState by masterViewModel.masterScreenUIState.collectAsState()
    MyFriendTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            MainContent(onSeeDetailClicked, masterViewModel)
            if (masterScreenUIState.displayingAbout) {
                displayAbout()
            }
        }
    }
}

fun displayAbout() {
    TODO("Not yet implemented")
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    onSeeDetailClicked: (cat: Cat) -> Unit,
    //onSeeDetailClicked:() -> Unit,
    viewModel: MasterViewModel,
) {
    val state = viewModel.masterScreenUIState.collectAsState()


    Log.e(TAG, "receipes value: ${state.value.cats}")
    remember {
        viewModel.catsRequested()
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        val (searchView, recyclerColumn) = createRefs()

        /*if (recipes?.size == 0) {
            Text("loading")
            viewModel.receipeRequested()
        } else {*/
        /*OutlinedTextField(
            modifier = Modifier.constrainAs(searchView) {
                top.linkTo(parent.top, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            value = state.value.searchValue,
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            placeholder = {
                Text(text = "Search")
            },
            onValueChange = { value -> viewModel.searchChange(value)},
        )*/
        LazyColumn(modifier = Modifier.constrainAs(recyclerColumn) {
            top.linkTo(searchView.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            items(
                state.value.cats
            ) {
                CatCard(onSeeDetailClicked, it)
            }
        }
        //}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier, state: MutableState<TextFieldValue>,
    placeHolder: String
) {

    TextField(value = state.value,
        onValueChange = { value -> state.value = value},
        modifier = modifier
    )


}