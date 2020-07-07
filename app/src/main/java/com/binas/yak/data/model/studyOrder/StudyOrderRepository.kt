package com.binas.yak.data.model.studyOrder

interface StudyOrderRepository {

    fun getStudyOrderList(): List<StudyOrder>
}