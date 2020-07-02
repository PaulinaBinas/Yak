package com.binas.yak.data.model.translation

interface TranslationRepository {

    fun getTranslation(id: Long): Translation
}