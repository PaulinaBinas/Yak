package com.binas.yak.di.builder

import android.provider.ContactsContract
import com.binas.yak.data.DatabaseModule
import com.binas.yak.data.model.achievements.AchievementsModule
import com.binas.yak.data.model.grammar.GrammarModule
import com.binas.yak.data.model.sign.SignModule
import com.binas.yak.data.model.studyDay.StudyDayModule
import com.binas.yak.data.model.studyOrder.StudyOrderModule
import com.binas.yak.data.model.translation.TranslationModule
import com.binas.yak.data.model.user.UserModule
import com.binas.yak.data.model.vocabulary.VocabularyModule
import com.binas.yak.di.module.AppModule
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
import com.binas.yak.ui.others.drawing.DrawingProvider
import com.binas.yak.ui.others.image.ImageProvider
import com.binas.yak.ui.settings.SettingsModule
import com.binas.yak.ui.settings.changeLanguage.ChangeLanguageModule
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageActivity
import com.binas.yak.ui.settings.changeLimit.ChangeLimitModule
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitActivity
import com.binas.yak.ui.settings.changePassword.ChangePasswordModule
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.studiedElements.StudiedElementsModule
import com.binas.yak.ui.studiedElements.calendar.CalendarModule
import com.binas.yak.ui.studiedElements.calendar.view.CalendarActivity
import com.binas.yak.ui.studiedElements.details.StudiedElementDetailsModule
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsActivity
import com.binas.yak.ui.studiedElements.view.StudiedElementsActivity
import com.binas.yak.ui.study.StudyModule
import com.binas.yak.ui.study.common.compareWriting.CompareWritingModule
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import com.binas.yak.ui.study.common.correct.CorrectModule
import com.binas.yak.ui.study.common.correct.view.CorrectActivity
import com.binas.yak.ui.study.common.incorrect.IncorrectModule
import com.binas.yak.ui.study.common.incorrect.view.IncorrectActivity
import com.binas.yak.ui.study.common.meaningCheck.MeaningCheckModule
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckActivity
import com.binas.yak.ui.study.common.pronunciationCheck.PronunciationCheckModule
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.common.reviseWriting.ReviseWritingModule
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.LearnGrammarWritingModule
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.GrammarStudyCardModule
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardActivity
import com.binas.yak.ui.study.grammar.reviseSound.GrammarReviseSoundModule
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.GrammarPronunciationCheckModule
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckActivity
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundActivity
import com.binas.yak.ui.study.grammar.reviseWriting.GrammarReviseWritingModule
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import com.binas.yak.ui.study.sign.learn.studyCard.SignStudyCardModule
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardActivity
import com.binas.yak.ui.study.sign.learn.writing.LearnSignWritingModule
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingActivity
import com.binas.yak.ui.study.sign.reviseSound.SignReviseSoundModule
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.SignReviseWithDecisionModule
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import com.binas.yak.ui.study.sign.reviseWriting.SignReviseWritingModule
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import com.binas.yak.ui.study.view.StudyActivity
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

    @ContributesAndroidInjector(modules = [(AchievementsActivityModule::class), (AchievementsModule::class), (DatabaseModule::class)])
    abstract fun bindAchievementsActivity(): AchievementsActivity

    @ContributesAndroidInjector(modules = [(GrammarReviseWritingModule::class), (GrammarModule::class), (DatabaseModule::class)])
    abstract fun bindGrammarReviseWritingActivity(): GrammarReviseWritingActivity

    @ContributesAndroidInjector(modules = [(SignReviseWritingModule::class), (SignModule::class), (DatabaseModule::class)])
    abstract fun bindSignReviseWritingActivity(): SignReviseWritingActivity

    @ContributesAndroidInjector(modules= [(SignReviseWithDecisionModule::class), (SignModule::class), (DatabaseModule::class)])
    abstract fun bindSignReviseWithDecisionActivity(): SignReviseWithDecisionActivity

    @ContributesAndroidInjector(modules = [(SignReviseSoundModule::class), (ImageProvider::class), (SignModule::class), (DatabaseModule::class)])
    abstract fun bindSignReviseSoundActivity(): SignReviseSoundActivity

    @ContributesAndroidInjector(modules = [(VocabularyReviseMeaningModule::class), (VocabularyModule::class), (TranslationModule::class), (DatabaseModule::class)])
    abstract fun bindVocabularyReviseMeaningActivity(): VocabularyReviseMeaningActivity

    @ContributesAndroidInjector(modules = [(VocabularyReviseSoundModule::class), (ImageProvider::class), (VocabularyModule::class), (DatabaseModule::class)])
    abstract fun bindVocabularyReviseSoundActivity(): VocabularyReviseSoundActivity

    @ContributesAndroidInjector(modules = [(VocabularyReviseWritingModule::class), (ImageProvider::class), (VocabularyModule::class), (DatabaseModule::class)])
    abstract fun bindVocabularyReviseWritingActivity(): VocabularyReviseWritingActivity

    @ContributesAndroidInjector(modules = [(LearnVocabularyWritingModule::class), (DrawingProvider::class), (StudyDayModule::class), (VocabularyModule::class), (DatabaseModule::class)])
    abstract fun bindLearnVocabularyWritingActivity(): LearnVocabularyWritingActivity

    @ContributesAndroidInjector(modules = [(VocabularyStudyCardModule::class), (ImageProvider::class), (VocabularyModule::class), (TranslationModule::class), (DatabaseModule::class)])
    abstract fun bindVocabularyStudyCardActivity(): VocabularyStudyCardActivity

    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(LoginModule::class), (UserModule::class), (DatabaseModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(AuthMenuModule::class)])
    abstract fun bindAuthMenuActivity(): AuthMenuActivity

    @ContributesAndroidInjector(modules = [(ResetPasswordModule::class)])
    abstract fun bindResetPasswordActivity(): ResetPasswordActivity

    @ContributesAndroidInjector(modules = [(SignupModule::class), (UserModule::class), (DatabaseModule::class)])
    abstract fun bindSignupActivity(): SignupActivity

    @ContributesAndroidInjector(modules = [(ChangeLimitModule::class)])
    abstract fun bindChangeLimitActivity(): ChangeLimitActivity

    @ContributesAndroidInjector(modules = [(SettingsModule::class)])
    abstract fun bindSettingsActivity(): SettingsActivity

    @ContributesAndroidInjector(modules = [(ChangeLanguageModule::class)])
    abstract fun bindChangeLanguageActivity(): ChangeLanguageActivity

    @ContributesAndroidInjector(modules = [(ChangePasswordModule::class)])
    abstract fun bindChangePasswordActivity(): ChangePasswordActivity

    @ContributesAndroidInjector(modules = [(CalendarModule::class)])
    abstract fun bindCalendarActivity(): CalendarActivity

    @ContributesAndroidInjector(modules = [(StudiedElementDetailsModule::class), (ImageProvider::class), (TranslationModule::class), (SignModule::class), (VocabularyModule::class), (GrammarModule::class), (DatabaseModule::class)])
    abstract fun bindStudiedElementDetailsActivity(): StudiedElementDetailsActivity

    @ContributesAndroidInjector(modules = [(StudiedElementsModule::class), (ImageProvider::class), (SignModule::class), (VocabularyModule::class), (GrammarModule::class), (TranslationModule::class), (DatabaseModule::class)])
    abstract fun bindStudiedElementsActivity(): StudiedElementsActivity

    @ContributesAndroidInjector(modules = [(LearnGrammarWritingModule::class), (DrawingProvider::class), (StudyDayModule::class), (GrammarModule::class), (DatabaseModule::class)])
    abstract fun bindLearnGrammarWritingActivity(): LearnGrammarWritingActivity

    @ContributesAndroidInjector(modules = [(GrammarStudyCardModule::class), (GrammarModule::class), (TranslationModule::class), (DatabaseModule::class)])
    abstract fun bindGrammarStudyCardActivity(): GrammarStudyCardActivity

    @ContributesAndroidInjector(modules = [(GrammarPronunciationCheckModule::class), (GrammarModule::class), (DatabaseModule::class)])
    abstract fun bindGrammarPronunciationCheckActivity(): GrammarPronunciationCheckActivity

    @ContributesAndroidInjector(modules = [(GrammarReviseSoundModule::class), (GrammarModule::class), (TranslationModule::class), (DatabaseModule::class)])
    abstract fun bindGrammarReviseSoundActivity(): GrammarReviseSoundActivity

    @ContributesAndroidInjector(modules = [(SignStudyCardModule::class), (ImageProvider::class), (SignModule::class), (TranslationModule::class), (DatabaseModule::class)])
    abstract fun bindSignStudyCardActivity(): SignStudyCardActivity

    @ContributesAndroidInjector(modules = [(LearnSignWritingModule::class), (DrawingProvider::class), (StudyDayModule::class), (ImageProvider::class), (SignModule::class), (DatabaseModule::class)])
    abstract fun bindLearnSignWritingActivity(): LearnSignWritingActivity

    @ContributesAndroidInjector(modules = [(CompareWritingModule::class), (ImageProvider::class), (SignModule::class), (VocabularyModule::class), (GrammarModule::class), (DatabaseModule::class)])
    abstract fun bindCompareWritingActivity(): CompareWritingActivity

    @ContributesAndroidInjector(modules = [(CorrectModule::class)])
    abstract fun bindCorrectActivity(): CorrectActivity

    @ContributesAndroidInjector(modules = [(IncorrectModule::class)])
    abstract fun bindIncorrectActivity(): IncorrectActivity

    @ContributesAndroidInjector(modules = [(MeaningCheckModule::class), (ImageProvider::class), (VocabularyModule::class), (DatabaseModule::class)])
    abstract fun bindMeaningCheckActivity(): MeaningCheckActivity

    @ContributesAndroidInjector(modules = [(PronunciationCheckModule::class), (SignModule::class), (VocabularyModule::class), (DatabaseModule::class), (ImageProvider::class)])
    abstract fun bindPronunciationCheckActivity(): PronunciationCheckActivity

    @ContributesAndroidInjector(modules = [(ReviseWritingModule::class), (DrawingProvider::class)])
    abstract fun bindReviseWritingActivity(): ReviseWritingActivity

    @ContributesAndroidInjector(modules = [(StudyModule::class), (StudyDayModule::class), (StudyOrderModule::class), (SignModule::class), (VocabularyModule::class), (GrammarModule::class), (DatabaseModule::class)])
    abstract fun bindStudyActivity(): StudyActivity
}