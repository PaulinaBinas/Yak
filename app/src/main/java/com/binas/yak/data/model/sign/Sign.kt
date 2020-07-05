package com.binas.yak.data.model.sign

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Sign", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("mnemonicDescriptionTranslationId")))])
data class Sign constructor(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var tibetanSign: String?,
    var audioFileName: String?,
    var mnemonicDescriptionTranslationId: Long?
): Parcelable {

}