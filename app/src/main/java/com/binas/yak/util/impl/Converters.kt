package com.binas.yak.util.impl

import androidx.room.TypeConverter
import com.binas.yak.data.model.RevisionType
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun toRevisionType(value: String) = enumValueOf<RevisionType>(value)

    @TypeConverter
    fun fromRevisionType(value: RevisionType) = value.name

    @TypeConverter
    fun toDate(value: String) = LocalDate.parse(value)

    @TypeConverter
    fun fromdate(value: LocalDate) = value.toString()
}