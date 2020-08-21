package com.binas.yak.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.achievements.view.AchievementsActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.studiedElements.view.StudiedElementsActivity
import com.binas.yak.ui.study.view.StudyActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class MainMenuTest: AbstractGuiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun opensSettings() {
        onView(withId(R.id.settingsButton)).perform(click())
        intended(hasComponent(SettingsActivity::class.java.name))
    }

    @Test
    fun opensStudiedElements() {
        onView(withId(R.id.studiedElementsButton)).perform(click())
        intended(hasComponent(StudiedElementsActivity::class.java.name))
    }

    @Test
    fun opensStudy() {
        onView(withId(R.id.studyButton)).perform(click())
        intended(hasComponent(StudyActivity::class.java.name))
    }

    @Test
    fun opensAchievements() {
        onView(withId(R.id.achievementButton)).perform(click())
        intended(hasComponent(AchievementsActivity::class.java.name))
    }
}