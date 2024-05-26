package ir.sajjadasadi.loginapp.Presenter

import ir.sajjadasadi.loginapp.Model.ModelMainActivity
import ir.sajjadasadi.loginapp.View.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) {
    fun onCreatePresenter() {
        view.onClickHandler(model.getID())
    }
}