package com.binas.yak.ui.authentication.signup.presenter

import com.binas.yak.data.model.user.User
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.view.SignupView
import com.binas.yak.ui.base.presenter.BasePresenter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupPresenterImpl<V: SignupView, I: SignupInteractor> @Inject internal constructor(interactor: I, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), SignupPresenter<V, I> {

    override fun addNewUser(email: String) {
        this.preferenceHelper.setCurrentUserEmail(email)
        interactor?.let {
            GlobalScope.launch {
                it.addUser(User(email))
                var user = it.getUser(email)
                user.id?.let { it1 -> preferenceHelper.setCurrentUserId(it1) }
                var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                firestore.collection("users").document(email).set(user)
            }
        }
    }
}