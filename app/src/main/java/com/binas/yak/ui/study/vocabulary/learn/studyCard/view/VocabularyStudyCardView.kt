package com.binas.yak.ui.study.vocabulary.learn.studyCard.view

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.view.BaseView

interface VocabularyStudyCardView: BaseView {

    fun loadImage()

    fun setContent(card: VocabularyStudyFlashcard, vocab: Vocabulary, text: Translation?)

    fun clickSoundButton()
}
