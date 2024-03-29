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
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReviseVocabularySoundTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<VocabularyReviseSoundActivity> =
        object : ActivityTestRule<VocabularyReviseSoundActivity>(VocabularyReviseSoundActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, VocabularyReviseSoundActivity::class.java).apply {
                    putExtra("id", 5L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun  displaysAllActivitiesCorrect() {
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(PronunciationCheckActivity::class.java.name))
        onView(withId(R.id.textView)).check(matches(withText("ང་")))
    }
}