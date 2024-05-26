package ir.sajjadasadi.loginapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.sajjadasadi.loginapp.Model.ModelMainActivity
import ir.sajjadasadi.loginapp.Presenter.PresenterMainActivity
import ir.sajjadasadi.loginapp.View.ViewMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this)
        setContentView(view.binding.root)
        val presenter = PresenterMainActivity(view, ModelMainActivity(this))
        presenter.onCreatePresenter()
    }
}