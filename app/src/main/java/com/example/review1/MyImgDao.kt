package com.example.flickrbrowserapproom_optional

import android.provider.ContactsContract
import androidx.room.*

@Dao
interface MyImgDao {

    @Query("SELECT * FROM myimg  ORDER BY id ASC")
    fun getAll(): List<MyImg>

    @Insert
    fun insert(item: MyImg)

    @Delete
    fun Delete(item: MyImg)

    @Update
    fun Update(item: MyImg)




}