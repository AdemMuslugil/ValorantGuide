package com.ademmuslugil.valorantguide.view.weapons

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.R
import com.ademmuslugil.valorantguide.model.weapons.Data
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy


@Composable
fun WeaponsScreen(
    weaponsViewModel: WeaponsViewModel = hiltViewModel(),
    navController: NavController
) {
    val isLoading by weaponsViewModel.isLoading.observeAsState(true)
    val weaponsList by weaponsViewModel.weaponsList.observeAsState()
    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            weaponsViewModel.getWeaponsList()
            TopAppBar(navController = navController)
            if (isLoading == true)
                ShowProgress()

            weaponsList?.data?.let {
                ItemListView(weaponList = it, navController = navController)
                weaponsViewModel.isLoading.value = false
            }
        }

    }
}

@Composable
private fun TopAppBar(navController: NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight(8 / 100f)
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
                text = stringResource(id = R.string.weapons_text),
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

@Composable
private fun ShowProgress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.red))
    }

}

@Composable
private fun ItemListView(weaponList: List<Data>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        contentPadding = PaddingValues(horizontal = 22.dp)
        ){
        items(weaponList.size){position ->
            ItemRow(weaponData = weaponList[position], navController = navController)
        }
    }
}

@Composable
private fun ItemRow(
    weaponData: Data?,
    navController: NavController,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(160.dp)
            .background(colorResource(id = R.color.background))
            .border(width = 1.dp, color = colorResource(id = R.color.red))
            .clickable {
                navController.navigate("weapon_detail_screen/${weaponData?.uuid}")
            }
    ){
        Text(
            text = weaponData?.displayName ?: "",
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        ImageFromUrl(url = weaponData?.displayIcon ?: "")
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageFromUrl(url: String) {
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
            .fitCenter()
    }
}