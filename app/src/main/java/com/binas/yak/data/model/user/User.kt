package com.binas.yak.data.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    var email: String
) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}
