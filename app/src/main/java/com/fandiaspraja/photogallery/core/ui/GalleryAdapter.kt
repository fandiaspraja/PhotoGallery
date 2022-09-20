package com.fandiaspraja.photogallery.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fandiaspraja.photogallery.R
import com.fandiaspraja.photogallery.core.domain.model.Gallery
import com.fandiaspraja.photogallery.databinding.ItemGalleryBinding
import java.util.ArrayList

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ListViewHolder>() {


    private var listData = ArrayList<Gallery>()
    var onItemClick: ((Gallery) -> Unit)? = null

    fun setData(newListData: List<Gallery>?) {
//        if (newListData == null) return
//        listData.clear()
        newListData?.let { listData.addAll(it) }

        notifyDataSetChanged()
    }

    fun clearData(){
        listData.clear()
        notifyDataSetChanged()
    }



    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGalleryBinding.bind(itemView)
        fun bind(data: Gallery){
            with(binding){

                Glide.with(itemView)
                    .load(data.urlImage)
                    .into(imgGallery)

                tvTitle.setText(data.userName)
                tvDesc.setText(data.description)

            }
        }

        init {

            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}