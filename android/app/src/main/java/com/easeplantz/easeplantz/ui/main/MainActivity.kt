package com.easeplantz.easeplantz.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.core.ui.main.MainAdapter
import com.easeplantz.easeplantz.databinding.ActivityMainBinding
import com.easeplantz.easeplantz.ui.image.ImageActivity
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val background = ResourcesCompat.getDrawable(resources, R.drawable.bg_gradient_green_blue, null)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ResourcesCompat.getColor(resources, android.R.color.transparent, null)
            window.setBackgroundDrawable(background)
        }
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