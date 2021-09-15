package com.example.android.moviesguru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.android.moviesguru.fragments.AllMoviesFragment
import com.example.android.moviesguru.fragments.FantasyFragment
import com.example.android.moviesguru.fragments.RomanceFragment
import com.example.android.moviesguru.fragments.SciFiFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var nvDrawer: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        drawerToolbar = findViewById(R.id.drawerToolbar)
        nvDrawer = findViewById(R.id.nvDrawer)

        setSupportActionBar(drawerToolbar)
        nvDrawer.setNavigationItemSelectedListener(this)
        toggle = ActionBarDrawerToggle(this, drawerLayout, drawerToolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val allMoviesFragment = AllMoviesFragment()
        supportFragmentManager.beginTransaction().replace(R.id.flFragmentHolder, allMoviesFragment).addToBackStack(null).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //This is Kotlin's version of a switch statement
        val allMoviesFragment = AllMoviesFragment()
        val fantasyFragment = FantasyFragment()
        val romanceFragment = RomanceFragment()
        val sciFiFragment = SciFiFragment()

        when(item.itemId){
            R.id.nav_all -> supportFragmentManager.beginTransaction().replace(R.id.flFragmentHolder, allMoviesFragment).addToBackStack(null).commit()
            R.id.nav_fantasy -> supportFragmentManager.beginTransaction().replace(R.id.flFragmentHolder, fantasyFragment).addToBackStack(null).commit()
            R.id.nav_romance -> supportFragmentManager.beginTransaction().replace(R.id.flFragmentHolder, romanceFragment).addToBackStack(null).commit()
            R.id.nav_sci_fi -> supportFragmentManager.beginTransaction().replace(R.id.flFragmentHolder, sciFiFragment).addToBackStack(null).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStackImmediate()
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
}