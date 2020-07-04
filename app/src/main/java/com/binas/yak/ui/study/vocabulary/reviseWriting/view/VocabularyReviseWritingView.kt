package com.binas.yak.ui.study.vocabulary.reviseWriting.view

import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseView

interface VocabularyReviseWritingView: BaseView {

    fun loadImage()

    fun setContent(card: VocabularyRevisionFlashcard, vocabulary: Vocabulary)
}