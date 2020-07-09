package com.binas.yak.data.model.userStudyDay

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.User

@Entity(tableName = "StudyDay_User", primaryKeys = ["userId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = StudyDay::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("studyDayId"))])
data class UserStudyDay (
    val userId: Long,
    val studyDayId: Long
)