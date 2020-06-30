package com.binas.yak.data.model.userAchievement

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "achievementId"])
data class UserAchievement (
    val userId: Long,
    val achievementId: Long
)