package com.example.gitgit.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


@BindingAdapter("loadImage")
fun CircleImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

@BindingAdapter("favorite")
fun ImageView.favorite(image : Int){
    setImageResource(image)
}
