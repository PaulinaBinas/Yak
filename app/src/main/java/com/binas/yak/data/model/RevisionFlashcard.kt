package com.binas.yak.data.model

import java.time.LocalDate

interface RevisionFlashcard {

    var retention: Double
    var nextDisplayTime: LocalDate?

    fun forget()

    fun reviseSuccessfully()
}