package com.binas.yak.settings

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.ui.settings.view.SettingsActivity
import org.junit.Rule

@LargeTest
class SettingsTest {

    @get:Rule
    var activityRule = ActivityTestRule(SettingsActivity::class.java)
}