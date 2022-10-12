package com.example.memeapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

    private fun loadMeme(){
        // Instantiate the RequestQueue.
        val ProgressBar = findViewById<ProgressBar>(R.id.ProgressBar)
        ProgressBar.visibility = View.VISIBLE
        currentImageUrl = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, currentImageUrl, null,
            { response ->
                val url =  response.getString("url")
                Glide.with(this).load(url).listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        ProgressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        ProgressBar.visibility = View.GONE
                        return false
                    }

                }).into(findViewById(R.id.memeImageView))
            },
            {
                Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue.
        MySingleton.getInstance(this,).addToRequestQueue(jsonObjectRequest)
    }

    fun nextMeme(view: View) {
        loadMeme()
    }
}