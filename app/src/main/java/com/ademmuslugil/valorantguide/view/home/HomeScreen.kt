package com.ademmuslugil.valorantguide.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ademmuslugil.valorantguide.R
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.model.HomeScreenItemModel


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    Surface(
        color = colorResource(id = R.color.background),
    ) {
        val itemList = viewModel.getItemList(LocalContext.current)
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(navController = navController)
            ItemListView(itemList = itemList, navController = navController)
        }
    }
}

@Composable
private fun TopAppBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(10 / 100f)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.valorant_guide),
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular))
            ),
            modifier = Modifier.weight(1f)
        )

        //Settings icon
        Image(
            painter = painterResource(id = R.drawable.ic_settings),
            contentDescription = "Settings Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    Navigation(navController = navController, route = "settings_screen")
                }
            )
    }
}

@Composable
private fun ItemListView(itemList: List<HomeScreenItemModel>, navController: NavController) {
    val agentText = stringResource(id = R.string.agents_text)
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 22.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemList) { item ->
            ItemRow(name = item.name, image = item.image) {
                if (item.name == agentText){
                    Navigation(navController = navController,"agent_screen")
                } else
                   Navigation(navController = navController,"weapons_screen")
            }
        }
    }
}

@Composable
private fun ItemRow(name: String, image: Int, listener: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 16.dp)
            .background(colorResource(id = R.color.background))
            .border(1.dp, colorResource(id = R.color.red))
            .clickable {
                //navigate
                listener.invoke()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            modifier = Modifier
                .weight(1f)
                .padding(start = 35.dp),
            color = Color.White,
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                fontSize = 28.sp
            )
        )

        //Category Image
        Image(
            painter = painterResource(id = image),
            contentDescription = "Category image",
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
        )
    }
}

private fun Navigation(navController: NavController, route: String){
    navController.navigate(route = route)
}

@Preview
@Composable
private fun HomeScreenPreview() {
    //HomeScreen()
}