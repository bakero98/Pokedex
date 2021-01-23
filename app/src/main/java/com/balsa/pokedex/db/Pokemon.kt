package com.balsa.pokedex.db

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pokemon_data_table")
data class Pokemon (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pokemon_id")
    val id: Int,

    @ColumnInfo(name = "pokemon_name")
    var name: String,

    @ColumnInfo(name = "pokemon_power")
    val power: Int,

    @ColumnInfo(name = "pokemon_type")
    val type: String,

    @ColumnInfo(name = "pokemon_image")
    val image: Bitmap
) : Parcelable