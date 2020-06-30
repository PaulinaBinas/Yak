package com.binas.yak.data.model.userStudyDay

import androidx.room.Entity

@Entity(primaryKeys = ["studyDayId", "userId"])
data class UserStudyDay (
    val studyDayId: Long,
    val userId: Long
)