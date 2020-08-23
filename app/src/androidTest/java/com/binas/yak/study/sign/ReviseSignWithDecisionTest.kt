package com.binas.yak.study.sign

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.ui.study.common.correct.view.CorrectActivity
import com.binas.yak.ui.study.common.incorrect.view.IncorrectActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReviseSignWithDecisionTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<SignReviseWithDecisionActivity> =
        object : ActivityTestRule<SignReviseWithDecisionActivity>(SignReviseWithDecisionActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, SignReviseWithDecisionActivity::class.java).apply {
                    putExtra("id", 1L)
                    putExtra("time", 100_000_000L)
                }
            }
        }

    @Test
    fun displaysCorrect() {
        onView(withText("ཀ")).perform(click())
        intended(hasComponent(CorrectActivity::class.java.name))
    }

    @Test
    fun displaysIncorrect() {
        onView(withText("ཁ")).perform(click())
        intended(hasComponent(IncorrectActivity::class.java.name))
    }
}