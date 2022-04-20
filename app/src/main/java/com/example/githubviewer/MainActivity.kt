package com.example.githubviewer

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.githubviewer.fragments.AuthFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { getNavigationController() }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setSupportActionBar(findViewById(R.id.toolbar))
        setContentView(R.layout.activity_main)
    }

    private fun getNavigationController(): NavController {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        return navHostFragment.navController
    }

    /*private fun navigateToAuth() {
        val action = AuthFragmentDirections.actionAuthorization()
        navController.navigate(action)
    }*/

    /*private fun navigateToRepoList() {
        val action = AuthFragmentDirections.actionAuthorization()
        navController.navigate(action)
    }*/
}