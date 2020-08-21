package com.binas.yak.auth

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.authentication.login.view.LoginActivity
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordActivity
import com.binas.yak.ui.main.view.MainActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class LoginTest: AbstractGuiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun opensResetPassword() {
        onView(withId(R.id.resetPasswordButton)).perform(click())
        intended(hasComponent(ResetPasswordActivity::class.java.name))
    }

    @Test
    fun logsIn() {
        onView(withId(R.id.emailEditText)).perform(replaceText("test@gmail.com"))
        onView(withId(R.id.passwordEditText)).perform(replaceText("test123"))
        onView(withId(R.id.signupButton)).perform(click())

        Thread.sleep(10_000)

        intended(hasComponent(MainActivity::class.java.name))
    }
}