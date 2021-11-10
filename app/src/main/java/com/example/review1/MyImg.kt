package com.example.flickrbrowserapproom_optional


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity(tableName = "myimg")
data class MyImg(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")  var id :Int?, // this is how can include id if needed
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "ischecked") var ischecked: Boolean = false)




