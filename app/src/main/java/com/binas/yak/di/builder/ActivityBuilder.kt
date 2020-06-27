package com.binas.yak.di.builder

import com.binas.yak.ui.achievements.AchievementsActivityModule
import com.binas.yak.ui.achievements.view.AchievementsActivity
import com.binas.yak.ui.study.grammar.reviseWriting.GrammarReviseWritingModule
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import com.binas.yak.ui.study.sign.reviseSound.SignReviseSoundModule
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.SignReviseWithDecisionModule
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import com.binas.yak.ui.study.sign.reviseWriting.SignReviseWritingModule
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(AchievementsActivityModule::class)])
    abstract fun bindAchievementsActivity(): AchievementsActivity

    @ContributesAndroidInjector(modules = [(GrammarReviseWritingModule::class)])
    abstract fun bindGrammarReviseWritingActivity(): GrammarReviseWritingActivity

    @ContributesAndroidInjector(modules = [(SignReviseWritingModule::class)])
    abstract fun bindSignReviseWritingActivity(): SignReviseWritingActivity

    @ContributesAndroidInjector(modules= [(SignReviseWithDecisionModule::class)])
    abstract fun bindSignReviseWithDecisionActivity(): SignReviseWithDecisionActivity

    @ContributesAndroidInjector(modules = [(SignReviseSoundModule::class)])
    abstract fun bindSignReviseSoundActivity(): SignReviseSoundActivity

}