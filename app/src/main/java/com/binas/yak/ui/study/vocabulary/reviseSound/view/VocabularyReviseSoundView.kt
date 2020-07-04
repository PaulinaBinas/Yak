package com.binas.yak.ui.study.vocabulary.reviseSound.view

import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseView

interface VocabularyReviseSoundView: BaseView {

    fun setContent(card: VocabularyRevisionFlashcard, vocabulary: Vocabulary)

    fun loadImage()
}