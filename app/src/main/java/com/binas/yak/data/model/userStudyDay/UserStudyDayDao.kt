package com.binas.yak.data.model.userStudyDay

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserStudyDayDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithStudyDays(userId: Long): LiveData<UserWithStudyDays>
}