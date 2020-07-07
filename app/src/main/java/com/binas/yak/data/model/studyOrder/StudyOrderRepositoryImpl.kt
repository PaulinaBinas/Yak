package com.binas.yak.data.model.studyOrder

import javax.inject.Inject

class StudyOrderRepositoryImpl @Inject internal constructor(var studyOrderDao: StudyOrderDao): StudyOrderRepository {

    override fun getStudyOrderList(): List<StudyOrder> {
        return studyOrderDao.getStudyOrderList()
    }
}