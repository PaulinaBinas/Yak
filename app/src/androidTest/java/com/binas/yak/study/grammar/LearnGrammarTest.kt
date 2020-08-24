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
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardActivity
import com.binas.yak.ui.study.view.StudyActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class LearnGrammarTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<GrammarStudyCardActivity> =
        object : ActivityTestRule<GrammarStudyCardActivity>(GrammarStudyCardActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, GrammarStudyCardActivity::class.java).apply {
                    putExtra("id", 2L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysTheStudyCardCorrectly() {
        onView(withId(R.id.sentenceTextView)).check(matches(withText("ང་མི་ཡིན།")))
        onView(withId(R.id.translationTextView)).check(matches(withText("I'm a human.")))
        onView(withId(R.id.writeItButton)).perform(click())
        intended(hasComponent(LearnGrammarWritingActivity::class.java.name))
        onView(withId(R.id.nextButton)).perform(click())
        intended(hasComponent(StudyActivity::class.java.name))
    }
}