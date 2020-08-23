package com.binas.yak.settings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageActivity
import com.yariksoffice.lingver.Lingver
import org.junit.Rule
import org.junit.Test

@LargeTest
class ChangeLanguageTest: AbstractGuiTest() {

    @get:Rule
    var activityRule = ActivityTestRule(ChangeLanguageActivity::class.java)

    @Test
    fun changesLanguage() {
        onView(withId(R.id.polishButton)).perform(click())
        intended(hasComponent(MainActivity::class.java.name))
        assert(Lingver.getInstance().getLanguage() == "pl")
    }
}