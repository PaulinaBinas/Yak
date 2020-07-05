package com.binas.yak.data.model.vocabulary

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Vocabulary", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId")))])
data class Vocabulary constructor(

    @PrimaryKey(autoGenerate = true) val id: Long,
    var tibetanWord: String?,
    var audioFileName: String?,
    var translationId: Long?
) : Parcelable