package com.ademmuslugil.valorantguide.view.agents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.R
import com.ademmuslugil.valorantguide.SharedViewModel
import com.ademmuslugil.valorantguide.model.AgentDetail
import com.ademmuslugil.valorantguide.model.agents.Ability
import com.ademmuslugil.valorantguide.model.agents.Data
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy


@Composable
fun AgentsScreen(
    agentsViewModel: AgentsViewModel = hiltViewModel(),
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val isLoading by agentsViewModel.isLoading.observeAsState(true)
    val agentList by agentsViewModel.agentsList.observeAsState()
    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TopAppBar()
            agentsViewModel.getAgentsFromApi(LocalContext.current)

            if (isLoading == true)
                ShowProgress()

            agentList?.data?.let {
                ItemListView(agentList = it, navController = navController, sharedViewModel = sharedViewModel)
                agentsViewModel.isLoading.value = false
            }
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
fun ShowProgress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.red))
    }

}

@Composable
fun ItemListView(agentList: List<Data>, navController: NavController,sharedViewModel: SharedViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2) //show 2 items per row,
        ,contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(agentList.size){position ->
            ItemRow(agentData = agentList[position], navController = navController, sharedViewModel = sharedViewModel, abilities = agentList[position].abilities)
        }
    }
}

@Composable
fun ItemRow(
   agentData: Data?,
   abilities: List<Ability>?,
   navController: NavController,
   sharedViewModel: SharedViewModel
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(colorResource(id = R.color.background))
            .border(width = 1.dp, color = colorResource(id = R.color.red))
            .clickable {
                val abilityList = arrayListOf<Ability>()
                if (abilities != null) {
                    for (ability in abilities){
                        abilityList.add(Ability(
                            description = ability.description,
                            displayIcon = ability.displayIcon,
                            displayName = ability.displayName,
                            null
                        ))
                    }
                }


                val agent = AgentDetail(
                    agentName = agentData?.displayName,
                    agentType = agentData?.role?.displayName,
                    agentImage = agentData?.fullPortrait,
                    agentDescription = agentData?.description,
                    ability = abilityList,
                    background = agentData?.background
                )
                sharedViewModel.addAgent(agent)
                navController.navigate("agent_detail_screen")
                }
    ){
        Text(
            text = agentData?.displayName ?: "",
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        ImageFromUrl(url = agentData?.fullPortrait ?: "")
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