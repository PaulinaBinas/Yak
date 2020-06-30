package com.binas.yak.data.model

import androidx.room.TypeConverter

enum class RevisionType(val type: String) {
    MEANING("meaning"),
    PRONUNCIATION("pronunciation"),
    WRITING("writing")
}