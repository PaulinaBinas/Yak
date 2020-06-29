package com.binas.yak.ui.studiedElements.calendar.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.studiedElements.calendar.interactor.CalendarInteractor
import com.binas.yak.ui.studiedElements.calendar.view.CalendarView
import javax.inject.Inject

class CalendarPresenterImpl<V: CalendarView, I: CalendarInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), CalendarPresenter<V, I> {
}