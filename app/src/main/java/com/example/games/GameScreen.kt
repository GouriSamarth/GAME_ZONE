package com.example.games


import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameScreen(viewModel: Retrofit= viewModel()) {
    val game = viewModel.games.collectAsState(initial = emptyList())

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black)
    {

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(Color.Blue),

                    title = {
                        Text("GAME ZONE", style = TextStyle(color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(9.dp))
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription = null, tint = Color.White ,modifier = Modifier.padding(5.dp))
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null,tint = Color.White ,modifier = Modifier.padding(5.dp))
                        }

                    }
                    ,
                    navigationIcon = {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription =null , modifier = Modifier
                            .padding(5.dp)
                            .size(28.dp), tint = Color.White)
                    }
                )
            },
        ) {value ->
            LazyColumn(modifier = Modifier.padding(value), ) {
                items(game.value) { game ->
                    Game(games = game)
                }
            }
        }
    }

    }


/*
LazyColumn{
    items(game.value){
            game->
        Game(games = game)
    }
}*/
@Composable
fun Game(games: Games) {
    var card by remember {
        mutableStateOf(false)
    }



        Card(modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(if (card) 590.dp else 200.dp)
            .padding(10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )

            {
                card = !card
            }

        )
        {

                val image =   rememberImagePainter(
                    data = games.thumbnail,
                    builder = {
                        crossfade(true) // Enable smooth crossfade transition between images
                        // Apply transformations if needed
                    }
                )

                Image(painter = image, contentDescription = "pic",
                    modifier = Modifier
                        .size(400.dp, 200.dp)
                        .padding(10.dp) // Adjust the size as needed
                        .clip(shape = RoundedCornerShape(6.dp)), // Apply clip or shape if needed
                    contentScale = ContentScale.Crop)




            Column {
                Row {
                    Text(text = "${games.title}, ${games.release_date}",
                        style = TextStyle(Color.Black),
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                }

                Column {
                    Text(text = "${games.short_description}",modifier = Modifier.padding(10.dp))
                    TextButton(onClick = { games.game_url })
                    {
                        Text(text = "${games.game_url}")

                    }

                    Text(text = "Developer:- ${games.developer} ", modifier = Modifier.padding(10.dp))
                    Text(text = "publisher:- ${games.publisher} ", modifier = Modifier.padding(10.dp))

                }
            }


        }
    }


