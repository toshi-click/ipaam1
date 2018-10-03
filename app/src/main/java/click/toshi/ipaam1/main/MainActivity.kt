package click.toshi.ipaam1.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import click.toshi.ipaam1.R
import click.toshi.ipaam1.databinding.MainActivityBinding
import click.toshi.ipaam1.di.StoreProvider
import click.toshi.ipaam1.util.ext.observe
import javax.inject.Inject
import android.content.Intent
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var storeProvider: StoreProvider
    @Inject
    lateinit var actionCreator: MainActionCreator
    private val store by lazy { storeProvider.get(this, MainStore::class) }
    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity) }
    private val adapter = MainAdapter()
    private val ownerName = "satorufujiwara"

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.onItemClicked = {
            actionCreator.showRepoDetailDialog(this, ownerName, it.name)
        }

        // ライセンス画面を表示する
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))

        store.repos.observe(this) {
            it ?: return@observe
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        actionCreator.fetchRepo(ownerName)
    }
}


