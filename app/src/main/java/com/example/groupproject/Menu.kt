package com.example.groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Menu : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.menubar)
        val navView: NavigationView = findViewById(R.id.nav)

        toggle = ActionBarDrawerToggle( this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText( applicationContext,"clicked",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java) // Replace with your target Activity
                    startActivity(intent)
                }
                // Add other cases for other menu items here
            }
            true // Consumed the event (Optional, but recommended practice)
        }


        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}