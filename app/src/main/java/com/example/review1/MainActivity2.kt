package com.example.review1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproom_optional.MyImageDatabase
import com.example.flickrbrowserapproom_optional.MyImg
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    lateinit var rv1:RecyclerView
    lateinit var all:ArrayList<MyImg>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        rv1 = findViewById(R.id.rv1)
        all = arrayListOf()

        var rr = MyImageDatabase.getInstance(this).MyImgDao().getAll()
        for (i in rr){
            all.add(i)
        }

        rv1.adapter = MyAdapter(this,all)
        rv1.layoutManager = LinearLayoutManager(this)

        imageView4.setOnClickListener {hideimg()}

    }

    fun showimg(img: String){
        Glide.with(this).load(img).into(imageView4)
        rv1.visibility = View.GONE
        imageView4.visibility = View.VISIBLE
    }

    fun hideimg(){
        rv1.visibility = View.VISIBLE
        imageView4.visibility = View.GONE
    }
}