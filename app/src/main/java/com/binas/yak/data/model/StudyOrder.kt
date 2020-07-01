package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudyOrder (
    @PrimaryKey val id: Long,
    var signRevisionId: Long?,
    var grammarRevisionId: Long?,
    var vocabularyRevisionId: Long?,
    var signStudyId: Long?,
    var grammarStudyId: Long?,
    var vocabularyStudyId: Long?
)