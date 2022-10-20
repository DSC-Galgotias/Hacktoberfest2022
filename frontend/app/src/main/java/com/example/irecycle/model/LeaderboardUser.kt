package com.example.irecycle.model

import android.graphics.drawable.Drawable

data class LeaderboardUser(
    val name:String,
    val score:Long,
    val image:Drawable,
    val rank:Int
)
