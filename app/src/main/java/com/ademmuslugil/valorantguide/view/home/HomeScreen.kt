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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ademmuslugil.valorantguide.model.HomeScreenItemModel


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    Surface(
        color = colorResource(id = R.color.background),
    ) {
        val itemList = viewModel.getItemList(LocalContext.current)
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar()
            ItemListView(itemList = itemList)
        }
    }
}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(10 / 100f),
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
            )
        )
    }
}

@Composable
fun ItemListView(itemList: List<HomeScreenItemModel>) {
    LazyColumn(
        contentPadding = PaddingValues(end = 0.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemList) { item ->
            ItemRow(name = item.name, image = item.image) {
                //TODO()
            }
        }
    }
}

@Composable
fun ItemRow(name: String, image: Int, listener: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(22.dp, 15.dp)
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}