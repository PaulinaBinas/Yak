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
import com.binas.yak.ui.authentication.signup.view.SignupActivity
import com.binas.yak.ui.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.After
import org.junit.Rule
import org.junit.Test

@LargeTest
class SignUpTest: AbstractGuiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SignupActivity::class.java)

    val email = "test123@test.com"

    @After
    fun clear() {
        FirebaseAuth.getInstance().currentUser?.email?.let {
            FirebaseFirestore.getInstance().collection("users").document(it).delete()
        }
        FirebaseAuth.getInstance().currentUser?.delete()
    }

    @Test
    fun signsUp() {
        onView(withId(R.id.emailEditText)).perform(replaceText(email))
        onView(withId(R.id.passwordEditText)).perform(replaceText("password123"))
        onView(withId(R.id.passwordRepeatEditText)).perform(replaceText("password123"))
        onView(withId(R.id.signupButton)).perform(click())

        Thread.sleep(10_000)

        intended(hasComponent(MainActivity::class.java.name))
        assert(FirebaseAuth.getInstance().currentUser?.email.equals(email))
        assert(FirebaseFirestore.getInstance().collection("users").document(email) != null)
    }
}