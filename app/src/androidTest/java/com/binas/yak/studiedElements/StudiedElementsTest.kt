package com.binas.yak.studiedElements

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
import com.binas.yak.ui.studiedElements.calendar.view.CalendarActivity
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class StudiedElementsTest: AbstractGuiTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<StudiedElementDetailsActivity> =
        object : ActivityTestRule<StudiedElementDetailsActivity>(StudiedElementDetailsActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, StudiedElementDetailsActivity::class.java).apply {
                    putExtra("signId", 1L)
                    putExtra("audio", "ka")
                    putExtra("sign", "ཀ")
                }
            }
        }

    @Test
    fun displaysAllActivitiesCorrectly() {
        //TODO include after fixing the animation not registering clicks issue
        //onView(allOf(withId(R.id.animationView), withContentDescription("sign1"))).check(matches(isCompletelyDisplayed())).perform(click())
        //intended(hasComponent(StudiedElementDetailsActivity::class.java.name))
        onView(withId(R.id.term)).check(matches(withText("ཀ")))
        onView(withId(R.id.calendarButton)).perform(click())
        intended(hasComponent(CalendarActivity::class.java.name))
    }
}