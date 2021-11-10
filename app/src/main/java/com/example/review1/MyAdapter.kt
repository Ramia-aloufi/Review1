package com.example.review1

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproom_optional.MyImageDatabase
import com.example.flickrbrowserapproom_optional.MyImg
import kotlinx.android.synthetic.main.cell1.view.*

class MyAdapter(val activity: Context,var item:ArrayList<MyImg>):RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {

    var TAG = "MyAdapter"
    class ItemViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cell1,parent,false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var items = item[position]


        holder.itemView.apply {
            textView.text = items.author
            Glide.with(activity).load(items.img).into(imageView)
            if(items.ischecked == true){
                imageView3.setColorFilter(Color.argb(255, 255, 50, 50));
            }
            if (activity is MainActivity) {
                ll.setOnClickListener { activity.showimg(items.img) }
            }
            if (activity is MainActivity2) {
                ll.setOnClickListener { activity.showimg(items.img) }
            }
            imageView3.setOnClickListener {
                if (!items.ischecked){
                    items.ischecked = true
                    Log.d(TAG,"$items")
                    MyImageDatabase.getInstance(activity).MyImgDao().insert(items)
                    MyImageDatabase.getInstance(activity).MyImgDao().Update(items)
                    imageView3.setColorFilter(Color.argb(255, 255, 50, 50));
                    notifyDataSetChanged()
                }else{
                    items.ischecked = false
                    Log.d(TAG,"$items")
                    MyImageDatabase.getInstance(activity).MyImgDao().Delete(items)
                    imageView3.setColorFilter(Color.parseColor("#d7d7d7"))
                    notifyDataSetChanged()
                }
            }


        }
}

    override fun getItemCount(): Int = item.size
}