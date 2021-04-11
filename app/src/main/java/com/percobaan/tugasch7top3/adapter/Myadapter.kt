package com.percobaan.tugasch7top3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.percobaan.tugasch7top3.databinding.PostItemBinding
import com.percobaan.tugasch7top3.model.PostResponseItem

class Myadapter: RecyclerView.Adapter<Myadapter.ViewHolder>() {
    private var content = mutableListOf<PostResponseItem>()
    lateinit var onEditClick : (PostResponseItem) -> Unit
    lateinit var onDeleteClick: (Int?) -> Unit

    fun setOnDeleteListerner(onClick : (Int?) -> Unit){
        this.onDeleteClick = onClick
    }
    fun setOnClickListerner(onClick : (PostResponseItem) -> Unit){
        this.onEditClick = onClick
    }
    fun setContent(post: List<PostResponseItem>){
        content.clear()
        content.addAll(post)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myadapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: PostItemBinding = PostItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingViewHolder(content[position])
    }

    override fun getItemCount(): Int = content.size

    inner class ViewHolder (var binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindingViewHolder(postResponseItem: PostResponseItem){
            binding.tvTitle.text = postResponseItem.title
            binding.tvCategories.text = postResponseItem.categories
            binding.tvContent.text = postResponseItem.content
            binding.ivEdit.setOnClickListener {
                onEditClick(postResponseItem)
            }
            binding.ivDelete.setOnClickListener {
                onDeleteClick(postResponseItem.id)
            }
        }
    }
}