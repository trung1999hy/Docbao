package com.tvt.doc_bao_online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.doc_bao_online.Adapter.NewAdapter
import com.tvt.doc_bao_online.databinding.ActivityMainBinding
import com.tvt.doc_bao_online.model.News
import com.tvt.doc_bao_online.paser.NewsParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val listnew = arrayListOf<News>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = NewAdapter(listnew)
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        lifecycleScope.launch(Dispatchers.IO) {
            listnew.addAll(NewsParser.parserNews("https://cdn.24h.com.vn/upload/rss/trangchu24h.rss"))
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }

        }

    }
}