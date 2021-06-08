package com.easeplantz.easeplantz.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.easeplantz.easeplantz.core.ui.main.MainAdapter
import com.easeplantz.easeplantz.databinding.ActivityMainBinding
import com.easeplantz.easeplantz.ui.image.ImageActivity
import com.easeplantz.easeplantz.ui.prediction.PredictionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_MODEL = "extra_model"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menus = viewModel.getMenus()

        val mainAdapter = MainAdapter()
        mainAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            intent.putExtra(EXTRA_MODEL, selectedData.model)
            startActivity(intent)
        }
        mainAdapter.setMenus(menus)

        binding.apply {
            rvPlants.layoutManager = LinearLayoutManager(this@MainActivity)
            rvPlants.setHasFixedSize(true)
            rvPlants.adapter = mainAdapter
        }
    }
}