package com.binas.yak.settings

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitActivity
import org.junit.Rule

@LargeTest
class ChangeNumberOfCardsTest {

    @get:Rule
    var activityRule = ActivityTestRule(ChangeLimitActivity::class.java)
}