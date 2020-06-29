package com.binas.yak.ui.studiedElements.calendar

import com.binas.yak.ui.studiedElements.calendar.interactor.CalendarInteractor
import com.binas.yak.ui.studiedElements.calendar.interactor.CalendarInteractorImpl
import com.binas.yak.ui.studiedElements.calendar.presenter.CalendarPresenter
import com.binas.yak.ui.studiedElements.calendar.presenter.CalendarPresenterImpl
import com.binas.yak.ui.studiedElements.calendar.view.CalendarView
import dagger.Module
import dagger.Provides

@Module
class CalendarModule {

    @Provides
    internal fun provideCalendarInteractor(interactor: CalendarInteractorImpl): CalendarInteractor = interactor

    @Provides
    internal fun provideCalendarPresenter(presenter: CalendarPresenterImpl<CalendarView, CalendarInteractor>): CalendarPresenter<CalendarView, CalendarInteractor> = presenter
}