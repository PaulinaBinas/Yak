package com.binas.yak.di.builder

import com.binas.yak.ui.achievements.AchievementsActivityModule
import com.binas.yak.ui.achievements.view.AchievementsActivity
import com.binas.yak.ui.authentication.authMenu.AuthMenuModule
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuActivity
import com.binas.yak.ui.authentication.login.LoginModule
import com.binas.yak.ui.authentication.login.view.LoginActivity
import com.binas.yak.ui.authentication.resetPassword.ResetPasswordModule
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordActivity
import com.binas.yak.ui.authentication.signup.SignupModule
import com.binas.yak.ui.authentication.signup.view.SignupActivity
import com.binas.yak.ui.main.MainModule
import com.binas.yak.ui.main.splash.SplashModule
import com.binas.yak.ui.main.splash.view.SplashActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.SettingsModule
import com.binas.yak.ui.settings.changeLanguage.ChangeLanguageModule
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageActivity
import com.binas.yak.ui.settings.changePassword.ChangePasswordModule
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.studiedElements.StudiedElementsModule
import com.binas.yak.ui.studiedElements.calendar.CalendarModule
import com.binas.yak.ui.studiedElements.calendar.view.CalendarActivity
import com.binas.yak.ui.studiedElements.details.StudiedElementDetailsModule
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsActivity
import com.binas.yak.ui.studiedElements.view.StudiedElementsActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.LearnGrammarWritingModule
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.GrammarStudyCardModule
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardActivity
import com.binas.yak.ui.study.grammar.reviseSound.GrammarReviseSoundModule
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.GrammarPronounciationCheckModule
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.view.GrammarPronounciationCheckActivity
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundActivity
import com.binas.yak.ui.study.grammar.reviseWriting.GrammarReviseWritingModule
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import com.binas.yak.ui.study.sign.reviseSound.SignReviseSoundModule
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.SignReviseWithDecisionModule
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import com.binas.yak.ui.study.sign.reviseWriting.SignReviseWritingModule
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.LearnVocabularyWritingModule
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.studyCard.VocabularyStudyCardModule
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardActivity
import com.binas.yak.ui.study.vocabulary.reviseMeaning.VocabularyReviseMeaningModule
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningActivity
import com.binas.yak.ui.study.vocabulary.reviseSound.VocabularyReviseSoundModule
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundActivity
import com.binas.yak.ui.study.vocabulary.reviseWriting.VocabularyReviseWritingModule
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingActivity
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

    @ContributesAndroidInjector(modules = [(VocabularyReviseMeaningModule::class)])
    abstract fun bindVocabularyReviseMeaningActivity(): VocabularyReviseMeaningActivity

    @ContributesAndroidInjector(modules = [(VocabularyReviseSoundModule::class)])
    abstract fun bindVocabularyReviseSoundActivity(): VocabularyReviseSoundActivity

    @ContributesAndroidInjector(modules = [(VocabularyReviseWritingModule::class)])
    abstract fun bindVocabularyReviseWritingActivity(): VocabularyReviseWritingActivity

    @ContributesAndroidInjector(modules = [(LearnVocabularyWritingModule::class)])
    abstract fun bindLearnVocabularyWritingActivity(): LearnVocabularyWritingActivity

    @ContributesAndroidInjector(modules = [(VocabularyStudyCardModule::class)])
    abstract fun bindVocabularyStudyCardActivity(): VocabularyStudyCardActivity

    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(LoginModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(AuthMenuModule::class)])
    abstract fun bindAuthMenuActivity(): AuthMenuActivity

    @ContributesAndroidInjector(modules = [(ResetPasswordModule::class)])
    abstract fun bindResetPasswordActivity(): ResetPasswordActivity

    @ContributesAndroidInjector(modules = [(SignupModule::class)])
    abstract fun bindSignupActivity(): SignupActivity

    @ContributesAndroidInjector(modules = [(SettingsModule::class)])
    abstract fun bindSettingsActivity(): SettingsActivity

    @ContributesAndroidInjector(modules = [(ChangeLanguageModule::class)])
    abstract fun bindChangeLanguageActivity(): ChangeLanguageActivity

    @ContributesAndroidInjector(modules = [(ChangePasswordModule::class)])
    abstract fun bindChangePasswordActivity(): ChangePasswordActivity

    @ContributesAndroidInjector(modules = [(CalendarModule::class)])
    abstract fun bindCalendarActivity(): CalendarActivity

    @ContributesAndroidInjector(modules = [(StudiedElementDetailsModule::class)])
    abstract fun bindStudiedElementDetailsActivity(): StudiedElementDetailsActivity

    @ContributesAndroidInjector(modules = [(StudiedElementsModule::class)])
    abstract fun bindStudiedElementsActivity(): StudiedElementsActivity

    @ContributesAndroidInjector(modules = [(LearnGrammarWritingModule::class)])
    abstract fun bindLearnGrammarWritingActivity(): LearnGrammarWritingActivity

    @ContributesAndroidInjector(modules = [(GrammarStudyCardModule::class)])
    abstract fun bindGrammarStudyCardActivity(): GrammarStudyCardActivity

    @ContributesAndroidInjector(modules = [(GrammarPronounciationCheckModule::class)])
    abstract fun bindGrammarPronounciationCheckActivity(): GrammarPronounciationCheckActivity

    @ContributesAndroidInjector(modules = [(GrammarReviseSoundModule::class)])
    abstract fun bindGrammarReviseSoundActivity(): GrammarReviseSoundActivity
}