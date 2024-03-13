package com.sebastiancorradi.myfriend.ui.details

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sebastiancorradi.myfriend.R
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.components.WindowInfo
import com.sebastiancorradi.myfriend.ui.components.rememberWindowInfo

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(
    cat: Cat?, viewModel: DetailsViewModel, handleBack: () -> Unit
) {
    val context = LocalContext.current
    val state = viewModel.detailsScreenUIState.collectAsState()
    cat?.let {
        remember() { viewModel.initRequested(it) }
        val windowInfo = rememberWindowInfo()
        if ((windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) || (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)) {
            drawSingleColumn(state.value, context, handleBack)
        } else {
            drawDoubleColumn(state.value, context, handleBack)
        }
    }
}


@Composable
fun drawSingleColumn(state: DetailsScreenUIState, context: Context, handleBack: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        val (mainColumn) = createRefs()
        Column(modifier = Modifier
            .fillMaxSize()
            .constrainAs(mainColumn) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxHeight()) {
            ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
                var (image, divider, catData, backButton) = createRefs()

                showCatImage(cat = state.cat ?: Cat(),
                    context = context,
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable(onClick = { })
                        .size(200.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth())

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(divider) {
                            top.linkTo(image.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                }

                showCatData(cat = state.cat ?: Cat(), modifier = Modifier.constrainAs(catData) {
                        top.linkTo(divider.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                showBackButton(handleBack = handleBack,
                    modifier = Modifier
                        .constrainAs(backButton) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                        .wrapContentWidth())
            }
        }
    }
}

@Composable
fun drawDoubleColumn(state: DetailsScreenUIState, context: Context, handleBack: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        val (mainRow) = createRefs()
        Row(modifier = Modifier
            .fillMaxSize()
            .constrainAs(mainRow) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxHeight()
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()

            ) {
                var (image, divider, catData, backButton) = createRefs()

                showCatImage(cat = state.cat ?: Cat(),
                    context = context,
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable(onClick = { })
                        .size(200.dp)
                        .height(200.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            width = Dimension.fillToConstraints
                        })

                Column(
                    modifier = Modifier
                        .constrainAs(divider) {
                            top.linkTo(image.top)
                            start.linkTo(image.end)
                            bottom.linkTo(image.bottom)
                            width = Dimension.fillToConstraints
                        }
                        .wrapContentWidth()
                ) {
                    Row() {
                        Spacer(modifier = Modifier.width(10.dp))
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.height(200.dp).width(1.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
                showCatData(cat = state.cat ?: Cat(), modifier = Modifier.constrainAs(catData) {
                        top.linkTo(image.top)
                        start.linkTo(divider.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    })
                showBackButton(handleBack = handleBack,
                    modifier = Modifier
                        .constrainAs(backButton) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .wrapContentWidth())
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun showCatImage(cat: Cat, context: Context, modifier: Modifier) {
    GlideImage(
        model = context.getString(R.string.cat_URL) + cat.id,
        contentDescription = context.getString(R.string.cat_image_content_desription),
        modifier = modifier
    )

}

@Composable
fun showCatData(cat: Cat, modifier: Modifier) {
    Column(modifier = modifier) {
        Row(){
            Text(
                "Id:",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.3f),
                textAlign = TextAlign.Start
            )
            Text(
                "${cat.id.toString()}",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.7f),
                textAlign = TextAlign.Start
            )
        }
        Row(){
            Text("Size:",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.3f),
                textAlign = TextAlign.Start)
            Text("${cat.size}",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.7f),
                textAlign = TextAlign.Start)
        }
        Row(){
            Text("Tags:",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.3f),
                textAlign = TextAlign.Start)
            Text("${cat.tags.toString()}",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.7f),
                textAlign = TextAlign.Start)
        }
        Row(){
            Text("Mimetype:",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.3f),
                textAlign = TextAlign.Start)
            Text("${cat.mimetype}",
                modifier = Modifier.fillMaxWidth()
                    .weight(0.7f),
                textAlign = TextAlign.Start)
        }
    }
}

@Composable
fun showBackButton(handleBack: () -> Unit, modifier: Modifier) {
    Button(modifier = modifier, onClick = { handleBack() }) {
        Text(text = "Back")
    }
}/*

Column {
        Text("Id: ${cat.id.toString()}", modifier = Modifier.constrainAs(textId) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth(), textAlign = TextAlign.Center)
        Text("Size: ${cat.size}", modifier = Modifier.constrainAs(textSize) {
                top.linkTo(textId.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth(), textAlign = TextAlign.Center)
        Text("Tags: ${cat.tags.toString()}", modifier = Modifier.constrainAs(textTags) {
                top.linkTo(textSize.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth(), textAlign = TextAlign.Center)
        Text("Mimetype: ${cat.mimetype}", modifier = Modifier.constrainAs(textMimeType) {
                top.linkTo(textTags.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth(), textAlign = TextAlign.Center)
    }
 */