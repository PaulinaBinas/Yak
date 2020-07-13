package com.binas.yak.data.model

import com.google.firebase.firestore.Exclude
import java.time.LocalDate

interface RevisionFlashcard: Flashcard {

    override val id: Long
    var retention: Double?
    var revisionType: RevisionType
    @set:Exclude @get:Exclude var nextDisplayTime: LocalDate?
    var interval: Long?
}