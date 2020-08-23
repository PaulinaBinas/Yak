package com.binas.yak.study.vocabulary

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.study.view.StudyActivity
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class LearnVocabularyTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<VocabularyStudyCardActivity> =
        object : ActivityTestRule<VocabularyStudyCardActivity>(VocabularyStudyCardActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, VocabularyStudyCardActivity::class.java).apply {
                    putExtra("id", 2L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysAllActivitiesCorrectly() {
        onView(withId(R.id.vocabularyTextView)).check(matches(withText("ང་")))
        onView(withId(R.id.translationTextView)).check(matches(withText("I")))
        onView(withId(R.id.writeItButton)).perform(click())
        intended(hasComponent(LearnVocabularyWritingActivity::class.java.name))
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(StudyActivity::class.java.name))
    }
}