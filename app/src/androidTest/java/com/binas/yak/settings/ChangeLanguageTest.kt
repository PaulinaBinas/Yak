package com.binas.yak.settings

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageActivity
import org.junit.Rule

@LargeTest
class ChangeLanguageTest {

    @get:Rule
    var activityRule = ActivityTestRule(ChangeLanguageActivity::class.java)
}