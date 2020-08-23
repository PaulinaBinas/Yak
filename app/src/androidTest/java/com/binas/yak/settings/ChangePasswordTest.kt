package com.binas.yak.settings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.AbstractGuiTest
import com.binas.yak.R
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordActivity
import com.google.firebase.auth.FirebaseAuth
import org.junit.Rule
import org.junit.Test

@LargeTest
class ChangePasswordTest: AbstractGuiTest() {

    @get:Rule
    var activityRule = ActivityTestRule(ChangePasswordActivity::class.java)

    val email = "test@gmail.com"

    val password = "test123"

    val newPassword = "test321"

    @Test
    fun changesPassword() {
        onView(withId(R.id.oldPassword)).perform(replaceText(password))
        onView(withId(R.id.newPassword)).perform(replaceText(newPassword))
        onView(withId(R.id.newPasswordRepeat)).perform(replaceText(newPassword))
        onView(withId(R.id.signupButton)).perform(click())

        var loggedInWithNewPassword = false
        FirebaseAuth.getInstance().signOut()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, newPassword).addOnSuccessListener { loggedInWithNewPassword = true }
        assert(loggedInWithNewPassword)
    }
}