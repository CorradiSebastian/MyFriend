package com.sebastiancorradi.myfriend.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sebastiancorradi.myfriend.R
import com.sebastiancorradi.myfriend.data.Cat

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    cat: Cat?,
    viewModel: DetailsViewModel
) {
    val context = LocalContext.current
    val state = viewModel.detailsScreenUIState.collectAsState()
    cat?.let {
        remember() { viewModel.initRequested(it) }



        ConstraintLayout(
            modifier = Modifier.fillMaxSize().padding(12.dp),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text("Id: ${state.value.cat?.tags.toString()}")
                Text("Owner: ${state.value.cat?.owner}")
                Text("Created At: ${state.value.cat?.createdAt}")
                Text("Updated At: ${state.value.cat?.upatedAt}")
                GlideImage(
                    model = context.getString(R.string.cat_URL) + it.id,
                    contentDescription = context.getString(R.string.cat_image_content_desription),
                    modifier = Modifier.padding(2.dp).clickable(onClick = { }).size(200.dp),
                )
            }
        }
    }
}
