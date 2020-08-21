package com.binas.yak.auth

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
import com.binas.yak.ui.authentication.login.view.LoginActivity
import com.binas.yak.ui.authentication.signup.view.SignupActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class AuthenticationMenuTest: AbstractGuiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(AuthMenuActivity::class.java)

    @Test
    fun opensLoginActivity() {
        onView(withId(R.id.logInButton)).perform(click())
        intended(hasComponent(LoginActivity::class.java.name))
    }

    @Test
    fun opensSignupActivity() {
        onView(withId(R.id.signUpButton)).perform(click())
        intended(hasComponent(SignupActivity::class.java.name))
    }
}