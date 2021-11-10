package com.example.review1

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapproom_optional.MyImg
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

//data class MyImg(val img:String,val author:String)

class MainActivity : AppCompatActivity() {

    lateinit var rv:RecyclerView
    lateinit var al:ArrayList<MyImg>
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv)
        url = "https://picsum.photos/v2/list"
        al = arrayListOf()
        requestAPI()
     imageView2.setOnClickListener {hideimg()}
    rv.adapter = MyAdapter(this,al)
    rv.layoutManager = LinearLayoutManager(this)
}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymnu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.faforitmnu -> startActivity(Intent(this,MainActivity2::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
    fun requestAPI(){
    CoroutineScope(Dispatchers.IO).launch{
        val data = async { CheckURL() }.await()

        if(data.isNotEmpty()){
            bindingToView(data)
        }
    }

}
    fun CheckURL():String{
    var url = ""
    try {
        url = URL(this.url).readText(Charsets.UTF_8)
    }catch (e: Exception){

    }
    return url
}

    suspend fun bindingToView(data:String){
    withContext(Dispatchers.Main){
        val json = JSONArray(data)
        for (i in 0 until json.length() ) {
            val title = json.getJSONObject(i).getString("author").toString()
            val img = json.getJSONObject(i).getString("download_url").toString()
            al.add(MyImg(null,img, title,false))
        }
        rv.adapter?.notifyDataSetChanged()

    }



}

    fun showimg(img: String){
        Glide.with(this).load(img).into(imageView2)
        rv.visibility = View.GONE
        imageView2.visibility = View.VISIBLE
    }

    fun hideimg(){
        rv.visibility = View.VISIBLE
        imageView2.visibility = View.GONE
    }
}