package com.binas.yak.data.model.grammar

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Grammar", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId")))])
data class Grammar constructor(
    @PrimaryKey val id: Long,
    var firstPartOfSentence: String?,
    var grammarPhase: String?,
    var secondPartOfSentence: String?,
    var audioFileName: String?,
    var translationId: Long?
): Parcelable