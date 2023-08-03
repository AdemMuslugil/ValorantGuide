package com.ademmuslugil.valorantguide.view.settings

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.R
import java.util.Locale

@Composable
fun SettingsScreen(
    navController: NavController,
    activity: Activity,
    viewModel: SettingsScreenViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background),
    ) {
        Column {
            TopAppBar(navController = navController)
            AddSettingsItem(viewModel = viewModel, activity = activity)
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
            //back button
            Image(
                painter = painterResource(id = R.drawable.back_arrow_red) ,
                contentDescription = "Back Arrow Icon",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            //settings text
            Text(
                text = stringResource(id = R.string.settings_text),
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
private fun AddSettingsItem(viewModel: SettingsScreenViewModel,activity: Activity){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .border(1.dp, color = colorResource(id = R.color.red)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.language_text),
            style = TextStyle(
                color = colorResource(id = R.color.red),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        DropdownText(viewModel = viewModel, activity = activity)

    }
}

@Composable
fun DropdownText(viewModel: SettingsScreenViewModel,activity: Activity) {
    Row(
        modifier = Modifier.padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val languageList = arrayOf("English","Türkçe")
        val contextForToast = LocalContext.current.applicationContext
        var selectedItem: String? by remember { mutableStateOf(null) }

        // state of the menu
        var expanded by remember {
            mutableStateOf(false)
        }

        //selected item name text
        Text(
            text = selectedItem ?: viewModel.getLanguage(),
            style = TextStyle(
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    expanded = true
                }
        )

        IconButton(onClick = {
            expanded = true
        }) {
            //Drop down arrow icon
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open Options"
            )
        }

        // drop down menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            // adding items
            languageList.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem = itemValue
                        Toast.makeText(contextForToast, itemValue, Toast.LENGTH_SHORT)
                            .show()
                        viewModel.setLanguage(itemValue)
                        viewModel.setAppLocale(activity = activity, viewModel = viewModel)
                        expanded = false
                    },
                    text = {
                        Text(text = itemValue)
                    })
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    //SettingsScreen()
}