package com.irmak.remoteanddbmovieapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.irmak.remoteanddbmovieapp.ui.PopularMovieFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = PopularMovieFragment()
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .commit()

        }
    }


