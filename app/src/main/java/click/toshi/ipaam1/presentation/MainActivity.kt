package click.toshi.ipaam1.presentation

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import click.toshi.ipaam1.R
import click.toshi.ipaam1.databinding.ActivityMainBinding
import click.toshi.ipaam1.presentation.common.activity.BaseActivity
import click.toshi.ipaam1.presentation.common.menu.DrawerMenu
import click.toshi.ipaam1.presentation.question.QuestionActionCreator
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var navigationController: NavigationController
    @Inject lateinit var drawerMenu: DrawerMenu
    @Inject lateinit var questionsActionCreator: QuestionActionCreator

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        drawerMenu.setup(binding.drawerLayout, binding.drawer, binding.toolbar, true)

        navigationController.navigateToQuestion()
        if (savedInstanceState == null) {
            questionsActionCreator.subscribeQuestionChange()
        }
    }

    override fun onBackPressed() {
        if (drawerMenu.closeDrawerIfNeeded()) {
            super.onBackPressed()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java)

        fun start(context: Context) {
            createIntent(context).let {
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(it)
            }
        }
    }
}
