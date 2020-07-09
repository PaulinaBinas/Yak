package com.binas.yak.util.impl

import androidx.room.TypeConverter
import com.binas.yak.data.model.RevisionType
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun toRevisionType(value: String): RevisionType? {
        when(value) {
            "pronunciation" -> return RevisionType.PRONUNCIATION
            "meaning" -> return RevisionType.MEANING
            "writing" -> return RevisionType.WRITING
        }
        return RevisionType.UNKNOWN
    }

    @TypeConverter
    fun fromRevisionType(value: RevisionType) = value.type

    @TypeConverter
    fun toDate(value: String?): LocalDate? = if (value != null) LocalDate.parse(value) else null

    @TypeConverter
    fun fromDate(value: LocalDate?) = value?.toString()
}