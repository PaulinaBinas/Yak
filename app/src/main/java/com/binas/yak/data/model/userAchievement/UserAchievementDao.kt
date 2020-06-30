package com.binas.yak.data.model.userAchievement

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.binas.yak.data.model.userAchievement.UserWithAchievements

@Dao
interface UserAchievementDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithAchievements(userId: Long): LiveData<UserWithAchievements>
}