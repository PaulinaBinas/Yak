package com.binas.yak.data.model

import java.time.LocalDate

interface RevisionFlashcard: Flashcard {

    var retention: Double?
    var revisionType: RevisionType
    var nextDisplayTime: LocalDate?
    var interval: Long?

    fun forget()

    fun reviseSuccessfully()
}