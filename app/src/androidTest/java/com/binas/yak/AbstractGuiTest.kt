package com.binas.yak

import androidx.test.espresso.intent.Intents
import org.junit.After
import org.junit.Before

abstract class AbstractGuiTest {

    @Before
    fun init() {
        Intents.init()
    }

    @After
    fun closeIntents() {
        Intents.release()
    }
}