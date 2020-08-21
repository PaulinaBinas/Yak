package com.binas.yak.settings

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordActivity
import org.junit.Rule

@LargeTest
class ChangePasswordTest {

    @get:Rule
    var activityRule = ActivityTestRule(ChangePasswordActivity::class.java)
}