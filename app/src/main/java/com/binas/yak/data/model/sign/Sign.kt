package com.binas.yak.data.model.sign

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation

@Entity(tableName = "Sign", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("mnemonicDescriptionTranslationId")))])
data class Sign(

    @PrimaryKey(autoGenerate = true) val id: Long,
    var tibetanSign: String?,
    var audioFileName: String?,
    var mnemonicDescriptionTranslationId: Long?
)