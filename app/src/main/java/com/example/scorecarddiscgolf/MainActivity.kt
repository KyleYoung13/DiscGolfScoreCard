package com.example.scorecarddiscgolf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scorecarddiscgolf.model.HoleFragment
import com.example.scorecarddiscgolf.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, mainFragment).commit()
        }
    }
