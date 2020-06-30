package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "sign", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("mnemonicDescriptionTranslationId"),
    onDelete = ForeignKey.CASCADE))])
data class Sign(

    @PrimaryKey(autoGenerate = true) val id: Long,
    var tibetanSign: String,
    var audioFileName: String,
    var mnemonicDescriptionTranslationId: Long
)