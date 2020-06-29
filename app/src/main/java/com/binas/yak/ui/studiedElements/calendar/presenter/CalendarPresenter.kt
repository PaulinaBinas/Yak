package com.binas.yak.ui.studiedElements.calendar.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.studiedElements.calendar.interactor.CalendarInteractor
import com.binas.yak.ui.studiedElements.calendar.view.CalendarView

interface CalendarPresenter<V: CalendarView, I: CalendarInteractor>: Presenter<V, I> {
}