package com.sebastiancorradi.myfriend.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.sebastiancorradi.myfriend.data.Cat
import com.sebastiancorradi.myfriend.ui.details.DetailsScreen
import com.sebastiancorradi.myfriend.ui.details.DetailsViewModel
import com.sebastiancorradi.myfriend.ui.master.MasterScreen
import com.sebastiancorradi.myfriend.ui.master.MasterViewModel

private lateinit var _navController: NavController
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Master.route,
) {
    _navController = navController
    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {
        composable(NavigationItem.Master.route) {
            MasterScreen(::navigateToDetails, masterViewModel = hiltViewModel<MasterViewModel>())
        }

        composable(NavigationItem.Details.route, arguments = listOf(navArgument("cat"){
            type = AssetParamType()
        })) {
                backStackEntry ->
            val cat = backStackEntry.arguments?.getParcelable<Cat>("cat")
            DetailsScreen(cat, viewModel = hiltViewModel<DetailsViewModel>())
        }
    }
}

fun navigateToDetails(cat: Cat){
    val json = Uri.encode(Gson().toJson(cat))
    _navController.navigate(Screen.DETAILS.name + "/$json")
}

class AssetParamType : NavType<Cat>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Cat? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Cat {
        return Gson().fromJson(value, Cat::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Cat) {
        bundle.putParcelable(key, value)
    }
}