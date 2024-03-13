package com.sebastiancorradi.myfriend.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sebastiancorradi.myfriend.R
import com.sebastiancorradi.myfriend.data.Cat

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatCard(displayDetails: (cat: Cat) -> Unit, cat: Cat) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable(interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { displayDetails(cat) }),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray, //Card background color
            //contentColor = Color.White  //Card content color,e.g.text
        )
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .fillMaxWidth(),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
            ) {
                val (mainCardRow) = createRefs()
                Row(modifier = Modifier.constrainAs(mainCardRow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    ConstraintLayout(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                    ) {
                        val (image, textOwner, textTags, textMimeType,petIcon) = createRefs()

                        //image
                        GlideImage(
                            model = context.getString(R.string.cat_URL) + cat.id,
                            contentDescription = context.getString(R.string.cat_image_content_desription),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                //  .clickable(onClick = { displayDetails(cat) })
                                .width(110.dp)
                                .height(110.dp)
                                .padding(5.dp)
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                },

                            )
                        Text(
                            text = "Size: " + cat.size,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                            ),
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .constrainAs(textOwner) {
                                    top.linkTo(parent.top)
                                    start.linkTo(image.end)
                                    end.linkTo(petIcon.start)
                                    width = Dimension.fillToConstraints
                                }
                                .padding(horizontal = 4.dp),
                        )
                        Text(
                            text = "Tags: " + cat.tags.toString(),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                            ),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .fillMaxWidth()
                                .constrainAs(textTags) {
                                    top.linkTo(textOwner.bottom)
                                    start.linkTo(image.end)
                                    end.linkTo(petIcon.start)
                                    width = Dimension.fillToConstraints
                                },

                        )
                        Text("Mimetype: ${cat.mimetype}",
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp)
                                .constrainAs(textMimeType) {
                                    top.linkTo(textTags.bottom)
                                    start.linkTo(image.end)
                                    end.linkTo(petIcon.start)
                                    width = Dimension.fillToConstraints
                                },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                                )
                        )
                        Image(
                            painterResource(R.drawable.ic_pet),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(40.dp)
                                .constrainAs(petIcon) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CatCard({}, Cat())
}