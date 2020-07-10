package com.binas.yak.data.model.studyDay

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "StudyDay")
data class StudyDay (@PrimaryKey(autoGenerate = true) var id: Long?) {
    var date: LocalDate? = LocalDate.now()
    var elementsStudied: Int? = 0
}