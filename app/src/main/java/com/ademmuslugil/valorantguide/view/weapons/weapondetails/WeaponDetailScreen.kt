package com.ademmuslugil.valorantguide.view.weapons.weapondetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.R
import com.ademmuslugil.valorantguide.model.weapons.Skin
import com.ademmuslugil.valorantguide.model.weapons.WeaponDetailModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy

@Composable
fun WeaponDetailScreen(
    weaponDetailViewModel: WeaponDetailViewModel = hiltViewModel(),
    id: String,
    navController: NavController
) {
    val weaponData by weaponDetailViewModel.weaponData.observeAsState()
    val isLoading by weaponDetailViewModel.isLoading.observeAsState(true)
    val scrollState = rememberScrollState()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background)
    ) {
        weaponDetailViewModel.getWeaponDetail(id = id)

        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
        ) {
            TopAppBar(navController = navController)

            if (isLoading == true)
                ShowProgress()

            weaponData?.data?.let { AddWeaponImage(weaponImage = it.displayIcon) }
            weaponData?.let {
                AddWeaponProperties(weaponData = it)
                weaponDetailViewModel.isLoading.value = false
            }

            if (!isLoading)
                Text(
                text = "Skins:",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.red),
                    fontStyle = FontStyle(R.font.bowlbyonesc_regular)
                ),
                modifier = Modifier.padding(top = 16.dp, start = 40.dp, end = 40.dp)
            )

            weaponData?.data?.skins?.let { ItemListView(skinList = it) }
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
                text = stringResource(R.string.weapon_details),
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
private fun AddWeaponImage(
    weaponImage: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        GlideImage(
            model = weaponImage,
            contentDescription = "Weapon Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
    }
}

@Composable
private fun AddWeaponProperties(
    weaponData: WeaponDetailModel,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AddFeatureText(feature = stringResource(id = R.string.name_text), weaponData.data.displayName ?: "null")
            AddFeatureText(feature = stringResource(id = R.string.type_text), weaponData.data.shopData?.category ?: "null")
            AddFeatureText(feature = stringResource(R.string.cost_string), weaponData.data.shopData?.cost.toString() ?: "null")
            AddFeatureText(feature = stringResource(R.string.magazine_string), weaponData.data.weaponStats?.magazineSize.toString() ?: "null")
            AddFeatureText(feature = stringResource(R.string.wall_penetration_text), weaponData.data.weaponStats?.wallPenetration ?: "null")
            AddFeatureText(feature = stringResource(R.string.fire_rate_text), weaponData.data.weaponStats?.fireRate.toString() ?: "null")
        }

    }
}

@Composable
private fun AddFeatureText(
    feature: String,
    text: String
){
    Column(modifier = Modifier
        .fillMaxWidth()) {
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
                )
            )

            Text(
                text = EditString(text = text),
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.White,
                    fontStyle = FontStyle(R.font.bowlbyonesc_regular),
                    textAlign = TextAlign.End
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.White)
        )
    }

}

@Composable
private fun EditString(text: String): String {
    //To remove unnecessary word from text
    val targetSubstring = "EWallPenetrationDisplayType::"
    return text.replace(targetSubstring, "")
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
private fun ItemListView(skinList: List<Skin>) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 22.dp)
    ){
        items(skinList.size){position ->
            skinList[position].displayName.apply {
                if (this == "Random Favorite Skin")
                    return@items
            }
            ItemRow(skinData = skinList[position])
        }
    }
}

@Composable
private fun ItemRow(
    skinData: Skin?
){
    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(top = 16.dp, start = 16.dp)
            .height(160.dp)
            .background(colorResource(id = R.color.background))
            .border(width = 1.dp, color = colorResource(id = R.color.red)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = skinData?.displayName ?: "",
            style = TextStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            textAlign = TextAlign.Center,
        )

        ImageFromUrl(
            url = skinData?.displayIcon ?: ""
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageFromUrl(url: String) {
    //Upload image from url with glide
    GlideImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        it.error(R.drawable.agents)
            .placeholder(R.drawable.agents)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .load(url)
            .fitCenter()
    }
}


@Preview
@Composable
private fun WeaponDetailScreenPreview() {
    //WeaponDetailScreen()
}