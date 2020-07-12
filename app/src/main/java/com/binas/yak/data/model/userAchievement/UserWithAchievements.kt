package com.binas.yak.data.model.userAchievement

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.achievements.Achievement
import com.binas.yak.data.model.user.User

data class UserWithAchievements (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserAchievement::class,
            parentColumn = "userId",
            entityColumn = "achievementId"
        )
    )
    val achievements: List<Achievement>
)