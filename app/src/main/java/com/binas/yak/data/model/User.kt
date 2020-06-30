package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "user")
data class User (
    var email: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
