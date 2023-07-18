package com.ademmuslugil.valorantguide.view.agents

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ademmuslugil.valorantguide.R
import com.ademmuslugil.valorantguide.model.agents.Data
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy


@Composable
fun AgentsScreen(
    agentsViewModel: AgentsViewModel = hiltViewModel()
) {

    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            val agentList by agentsViewModel.agentsList.observeAsState()
            TopAppBar()
            agentsViewModel.getAgentsFromApi(LocalContext.current)
            agentList?.data?.let { ItemListView(agentList = it) }
        }

    }
}

@Composable
private fun TopAppBar() {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight(8 / 100f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_valorant_logo) ,
                contentDescription = "Valorant icon",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun ItemListView(agentList: List<Data>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2) //show 2 items per row,
        ,contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(agentList.size){position ->
            ItemRow(agentName = agentList[position].displayName, agentImage = agentList[position].fullPortrait ?: "")
        }
    }
}

@Composable
fun ItemRow(
    agentName: String,
    agentImage: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(colorResource(id = R.color.background))
            .border(width = 1.dp, color = colorResource(id = R.color.red))
            .clickable {
                //TODO
            }
    ) {
        Text(
            text = agentName,
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        ImageFromUrl(url = agentImage)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageFromUrl(url: String) {
    //Upload image from url with glide
    GlideImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    ){
        it.error(R.drawable.agents)
            .placeholder(R.drawable.agents)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .load(url)
            .centerCrop()
    }
}

@Preview
@Composable
fun AgentsScreenPreview() {
    AgentsScreen()
}