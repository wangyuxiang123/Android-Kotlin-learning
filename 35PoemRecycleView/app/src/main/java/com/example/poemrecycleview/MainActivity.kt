package com.example.poemrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.poemrecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    var adapter: PoemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val spacingInPixels = 8
        binding.rv.addItemDecoration(SpacesItemDecoration(spacingInPixels))
        adapter = PoemAdapter(PoemLibrary.getPoemList())
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.layout_manager, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.mnuGridLayout -> changeToGridLayout()
            R.id.mnuLinerLayout -> changeToLinerLayout()
            R.id.mnuStaggerLayout -> changeToStaggerLayout()
        }
        return true

    }

    private fun changeToGridLayout() {
        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = adapter
    }

    private fun changeToLinerLayout() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
    }

    private fun changeToStaggerLayout() {
        binding.rv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter
    }
}