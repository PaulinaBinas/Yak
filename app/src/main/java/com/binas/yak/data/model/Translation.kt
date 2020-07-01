package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "Translation")
data class Translation(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var polish: String,
    var english: String
)