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
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuActivity
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageActivity
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitActivity
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.google.firebase.auth.FirebaseAuth
import org.junit.Rule
import org.junit.Test

@LargeTest
class SettingsTest: AbstractGuiTest() {

    @get:Rule
    var activityRule = ActivityTestRule(SettingsActivity::class.java)

    val email = "test@gmail.com"

    val password = "test123"

    @Test 
    fun opensChangeCardLimit() {
        onView(withId(R.id.changeCardsLimitButton)).perform(click())
        intended(hasComponent(ChangeLimitActivity::class.java.name))
    }
    
    @Test
    fun opensChangeLanguage() {
        onView(withId(R.id.changeLangButton)).perform(click())
        intended(hasComponent(ChangeLanguageActivity::class.java.name))
    }

    @Test
    fun opensChangePassword() {
        onView(withId(R.id.changePasswordButton)).perform(click())
        intended(hasComponent(ChangePasswordActivity::class.java.name))
    }

    @Test
    fun logsOut() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        onView(withId(R.id.logOutButton)).perform(click())
        intended(hasComponent(AuthMenuActivity::class.java.name))
        assert(FirebaseAuth.getInstance().currentUser == null)
    }
}