package com.binas.yak.data.model.userStudyDay

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.user.User

data class UserWithStudyDays (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserStudyDay::class,
            parentColumn = "userId",
            entityColumn = "studyDayId"
        )
    )
    val studyDays: List<StudyDay>
)