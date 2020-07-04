package com.binas.yak.ui.study.vocabulary.reviseMeaning.view

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseView

interface VocabularyReviseMeaningView: BaseView {

    fun setContent(card: VocabularyRevisionFlashcard, word: Vocabulary, translation: Translation?)

    fun clickSoundButton()
}