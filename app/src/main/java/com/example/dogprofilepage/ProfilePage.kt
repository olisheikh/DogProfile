package com.example.dogprofilepage

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.layout.layoutId

@Composable
fun Profile(){
    Card(
        elevation = 6.dp,
        modifier = Modifier.fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        BoxWithConstraints() {
            val constraits = if (minWidth < 600.dp){
                portraitConstraints(margin = 16.dp)
            }else{
                landScapeConstraints(margin = 16.dp)
            }
            ConstraintLayout(constraits) {
                Image(
                    painter = painterResource(id = R.drawable.husky),
                    contentDescription = "Husky",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = CircleShape
                        )
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Husky",
                    modifier = Modifier.layoutId("name")
                )
                Text(
                    text = "USA",
                    modifier = Modifier.layoutId("countryName")
                )
                Row( // followers row
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowState")
                ) {
                    ProfileView("150", "Followers")
                    ProfileView("100", "Following")
                    ProfileView("30", "Posts")
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("buttonUser")
                ) {
                    Text(text = "Follow user")

                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.layoutId("buttonDirect")
                ) {
                    Text(text = "Direct message")
                }
            }
        }
    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val name = createRefFor("name")
        val countryName = createRefFor("countryName")
        val rowState = createRefFor("rowState")
        val buttonUser = createRefFor("buttonUser")
        val buttonDirect = createRefFor("buttonDirect")
        val guide = createGuidelineFromTop(0.1f)

        constrain(image){
            top.linkTo(guide)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(name){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(countryName){
            top.linkTo(name.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowState){
            top.linkTo(countryName.bottom)
        }
        constrain(buttonUser){
            top.linkTo(rowState.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(buttonDirect.start)
            width = Dimension.wrapContent
        }
        constrain(buttonDirect){
            top.linkTo(rowState.bottom, margin = margin)
            start.linkTo(buttonUser.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }

    }
}

private fun landScapeConstraints(margin: Dp): ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val name = createRefFor("name")
        val countryName = createRefFor("countryName")
        val rowState = createRefFor("rowState")
        val buttonUser = createRefFor("buttonUser")
        val buttonDirect = createRefFor("buttonDirect")

        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(name){
            top.linkTo(image.bottom)
            start.linkTo(image.start)
        }
        constrain(countryName){
            top.linkTo(name.bottom)
            start.linkTo(name.start)
            end.linkTo(name.end)
        }
        constrain(rowState){
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(buttonUser){
            top.linkTo(rowState.bottom)
            start.linkTo(rowState.start)
            end.linkTo(buttonDirect.start)
            bottom.linkTo(countryName.bottom)
            width = Dimension.wrapContent
        }
        constrain(buttonDirect){
            top.linkTo(rowState.bottom)
            start.linkTo(buttonUser.end)
            end.linkTo(rowState.end)
            bottom.linkTo(countryName.bottom)
            width = Dimension.wrapContent
        }
    }

}

@Composable
fun ProfileView(follower: String, title: String){
    Column(){
        Text(
            text = follower,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title
        )
    }
}