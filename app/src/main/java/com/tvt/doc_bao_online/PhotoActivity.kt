package com.tvt.doc_bao_online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tvt.doc_bao_online.Adapter.PhotoAdapter
import com.tvt.doc_bao_online.databinding.ActivityPhotoBinding
import com.tvt.doc_bao_online.model.Photo
import com.tvt.doc_bao_online.paser.PhotoParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoActivity : AppCompatActivity() {
    private val listphoto= arrayListOf<Photo>()
    private lateinit var binding: ActivityPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityPhotoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter=PhotoAdapter(listphoto)
        binding.rcPhoto.adapter=adapter
        binding.rcPhoto.layoutManager=LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launch(Dispatchers.IO){
            listphoto.addAll(PhotoParser.parser("https://jsonplaceholder.typicode.com/photos"))
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
    }
}