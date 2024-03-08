package com.sebastiancorradi.myfriend.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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
    viewModel: DetailsViewModel,
    handleBack: () -> Unit
) {
    val context = LocalContext.current
    val state = viewModel.detailsScreenUIState.collectAsState()
    cat?.let {
        remember() { viewModel.initRequested(it) }
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
                    var (image, textId, textSize, textTags, textMimeType, backButton) = createRefs()

                    GlideImage(
                        model = context.getString(R.string.cat_URL) + it.id,
                        contentDescription = context.getString(R.string.cat_image_content_desription),
                        modifier = Modifier
                            .padding(2.dp)
                            .clickable(onClick = { })
                            .size(200.dp)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth()
                    )
                    Text("Id: ${state.value.cat?.id.toString()}",
                        modifier = Modifier
                            .constrainAs(textId) {
                                top.linkTo(image.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center)
                    Text("Size: ${state.value.cat?.size}",
                        modifier = Modifier
                            .constrainAs(textSize) {
                                top.linkTo(textId.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center)
                    Text("Tags: ${state.value.cat?.tags.toString()}",
                        modifier = Modifier
                            .constrainAs(textTags) {
                                top.linkTo(textSize.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center)
                    Text("Mimetype: ${state.value.cat?.mimetype}",
                        modifier = Modifier
                            .constrainAs(textMimeType) {
                                top.linkTo(textTags.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Button(modifier = Modifier
                        .constrainAs(backButton) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                        .wrapContentWidth(),
                        onClick = {handleBack()})
                    {
                        Text(text = "Back")
                    }
                }
            }
        }
    }
}
