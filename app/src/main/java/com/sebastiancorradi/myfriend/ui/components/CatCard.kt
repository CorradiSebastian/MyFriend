package com.sebastiancorradi.myfriend.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sebastiancorradi.myfriend.R
import com.sebastiancorradi.myfriend.data.Cat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatCard(displayDetails: (cat: Cat) -> Unit, cat: Cat) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray, //Card background color
            //contentColor = Color.White  //Card content color,e.g.text
        )
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)) {


            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    verticalArrangement = Arrangement.Top,

                    ) {
                    Text(
                        text = cat.owner,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                    Text(
                        text = cat.tags.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                    Button(modifier = Modifier.wrapContentSize(), onClick = {
                        displayDetails(cat)
                    }) {
                        Text(text = context.getString(R.string.see_details))
                    }
                }
                Spacer(Modifier.weight(1f))
                //image
                GlideImage(
                    model = context.getString(R.string.cat_URL) + cat.id,
                    contentDescription = context.getString(R.string.cat_image_content_desription),
                    modifier = Modifier.padding(2.dp).clickable(onClick = {  }).size(50.dp),
                )

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CardPreview() {
    CatCard({}, Cat())
}