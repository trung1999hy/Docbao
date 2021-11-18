package com.tvt.doc_bao_online.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tvt.doc_bao_online.R
import com.tvt.doc_bao_online.databinding.ItemNewBinding
import com.tvt.doc_bao_online.model.Photo

class PhotoAdapter(private val photoo:List<Photo>):RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
      return ViewHolder(ItemNewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
      holder.bindd(photoo[position])
    }

    override fun getItemCount(): Int = photoo.size

    class ViewHolder(private val binding: ItemNewBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindd(photo:Photo){
        Glide.with(binding.imgPreview).load(photo.thumbnailUrl).centerCrop().error(R.drawable.ic_launcher_background).into(binding.imgPreview)
            binding.tvTitle.text=photo.tile
            binding.tvDescription.text=photo.url
            binding.tvDate.text=photo.album.toString()

        }

    }
}