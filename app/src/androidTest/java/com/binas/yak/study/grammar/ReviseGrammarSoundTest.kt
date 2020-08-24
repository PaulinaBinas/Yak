package com.binas.yak.study.grammar

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
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckActivity
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReviseGrammarSoundTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<GrammarReviseSoundActivity> =
        object : ActivityTestRule<GrammarReviseSoundActivity>(GrammarReviseSoundActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, GrammarReviseSoundActivity::class.java).apply {
                    putExtra("id", 3L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysAllActivitiesCorrectly() {
        onView(withId(R.id.grammarTextView)).check(matches(withText("ང་མི་ཡིན།")))
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(GrammarPronunciationCheckActivity::class.java.name))
        onView(withId(R.id.grammarTextView)).check(matches(withText("ང་མི་ཡིན།")))
    }
}