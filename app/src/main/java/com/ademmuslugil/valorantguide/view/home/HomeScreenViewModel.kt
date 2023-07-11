package com.ademmuslugil.valorantguide.view.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ademmuslugil.valorantguide.R
import com.ademmuslugil.valorantguide.model.HomeScreenItemModel
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(): ViewModel() {

    private val itemList = arrayListOf<HomeScreenItemModel>()

    private fun addItem(context: Context): ArrayList<HomeScreenItemModel> {
        itemList.clear()

        itemList.add(
            HomeScreenItemModel(
                name = context.getString(R.string.agents_text),
                image = R.drawable.agents
            )
        )
        itemList.add(
            HomeScreenItemModel(
                name = context.getString(R.string.weapons_text),
                image = R.drawable.weapons
            )
        )

        return itemList
    }

    fun getItemList(context: Context): ArrayList<HomeScreenItemModel> {
        return addItem(context = context)
    }
}