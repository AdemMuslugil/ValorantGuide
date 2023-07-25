package com.ademmuslugil.valorantguide.view.agents.agentdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.R
import com.ademmuslugil.valorantguide.SharedViewModel
import com.ademmuslugil.valorantguide.model.agents.Ability
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun AgentDetailsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val agentDetail = sharedViewModel.agent

    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()) {
            TopAppBar(navController = navController)
            AddAgentImage(
                agentImage = agentDetail?.agentImage ?: "",
                background = agentDetail?.background ?: ""
            )
            AddAgentProperties(
                agentName = agentDetail?.agentName ?: "",
                typeText = agentDetail?.agentType ?: "",
                description = agentDetail?.agentDescription ?: ""
            )

            //Abilities title - red
            Text(
                text = stringResource(R.string.abilities_text),
                style = TextStyle(
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.red),
                    fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
            )
            InitAllAbilities(ability = agentDetail?.ability)
        }
    }

}

@Composable
private fun TopAppBar(navController: NavController) {
    Column{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight(8 / 100f)
                .defaultMinSize(minHeight = 60.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow_red) ,
                contentDescription = "Back Arrow Icon",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Text(
                text = stringResource(R.string.agent_details),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddAgentImage(
    agentImage: String,
    background: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            model = background,
            contentDescription = "Agent Background" +
                    " Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .alpha(0.7F)
                .fillMaxWidth()
        )

        GlideImage(
            model = agentImage,
            contentDescription = "Agent Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
    }
}

@Composable
fun AddAgentProperties(
    agentName: String,
    typeText: String,
    description: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 20.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AddFeatureText(feature = stringResource(id = R.string.name_text), agentName)
            AddFeatureText(feature = stringResource(id = R.string.type_text), typeText)
            AddFeatureText(feature = stringResource(id = R.string.description_text), description)
        }
        
    }
}

@Composable
fun AddFeatureText(
    feature: String,
    text: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Text(
            text = feature,
            style = TextStyle(
                fontSize = 15.sp,
                color = colorResource(id = R.color.red),
                fontStyle = FontStyle(R.font.bowlbyonesc_regular),
            ),
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.White,
                fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddAgentAbilities(
    abilityName: String,
    iconUrl:String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .border(1.dp, color = colorResource(id = R.color.red))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 16.dp, start = 8.dp)
        ) {
            //Ability icon
            GlideImage(
                model = iconUrl,
                contentDescription = "Ability Icon",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            //Ability name
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.name_text),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.red),
                        fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )

                Text(
                    text = abilityName,
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.white),
                        fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }

        //Description
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, bottom = 16.dp, end = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.description_text),
                style = TextStyle(
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.red),
                    fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                )
            )

            Text(
                text = description,
                style = TextStyle(
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.white),
                    fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun InitAllAbilities(ability: List<Ability>?){
    if (ability != null){
        ability[0].apply {
            AddAgentAbilities(
                abilityName = this.displayName ?: "",
                iconUrl =this.displayIcon ?: "",
                description = this.description ?: ""
            )
        }

        ability[1].apply {
            AddAgentAbilities(
                abilityName = this.displayName ?: "",
                iconUrl =this.displayIcon ?: "",
                description = this.description ?: ""
            )
        }

        ability[2].apply {
            AddAgentAbilities(
                abilityName = this.displayName ?: "",
                iconUrl =this.displayIcon ?: "",
                description = this.description ?: ""
            )
        }

        ability[3].apply {
            AddAgentAbilities(
                abilityName = this.displayName ?: "",
                iconUrl =this.displayIcon ?: "",
                description = this.description ?: ""
            )
        }
    }
}

@Preview
@Composable
fun AgentDetailScreenPreview() {
    //AgentDetailsScreen()
}