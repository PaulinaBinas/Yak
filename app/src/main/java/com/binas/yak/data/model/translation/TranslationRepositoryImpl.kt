package com.binas.yak.data.model.translation

import javax.inject.Inject

class TranslationRepositoryImpl @Inject internal constructor(var translationDao: TranslationDao): TranslationRepository{

    override fun getTranslation(id: Long): Translation {
        return translationDao.getTranslationById(id)
    }
}