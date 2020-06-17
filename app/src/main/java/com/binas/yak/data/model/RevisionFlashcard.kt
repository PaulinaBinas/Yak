package com.binas.yak.data.model

import java.time.LocalDate

interface RevisionFlashcard {

    var retention: Double
    var nextDisplayTime: LocalDate?
    var interval: Long?

    fun forget()

    fun reviseSuccessfully()
}