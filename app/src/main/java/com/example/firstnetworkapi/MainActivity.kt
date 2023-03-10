package com.example.firstnetworkapi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnetworkapi.adapter.DetailsAdapter
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.firstnetworkapi.databinding.ActivityMainBinding
import com.example.firstnetworkapi.service.Network
import com.example.firstnetworkapi.viewmodel.DetailsViewModel
import com.example.firstnetworkapi.viewmodel.SchoolsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


         val button = findViewById<Button>(R.id.morebutton)
                    button.setOnClickListener{

                        val intent = Intent(this, DetailsViewModel)
                        startActivity(intent)

        }


        val navHost = supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
        setupActionBarWithNavController(navHost.navController)
    }




    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }







}


