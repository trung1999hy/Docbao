package com.tvt.doc_bao_online.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tvt.doc_bao_online.databinding.ItemNewBinding
import com.tvt.doc_bao_online.model.News

class NewAdapter(private val listnew:List<News>):RecyclerView.Adapter<NewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapter.ViewHolder {
        return ViewHolder(ItemNewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewAdapter.ViewHolder, position: Int) {
       holder.bindd(listnew[position])
    }

    override fun getItemCount(): Int = listnew.size

    class ViewHolder(private val binding: ItemNewBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindd(news:News){
            Glide.with(binding.imgPreview).load(news.image)
                .centerCrop()
                .into(binding.imgPreview)
            binding.tvTitle.text=news.title
            binding.tvDescription.text=news.description
            binding.tvDate.text=news.datet

        }

    }

}