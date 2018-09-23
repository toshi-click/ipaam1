package click.toshi.ipaam1.main

import android.arch.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import click.toshi.ipaam1.data.entity.Repo
import click.toshi.ipaam1.flux.Store
import click.toshi.ipaam1.util.ext.toLiveData
import javax.inject.Inject

class MainStore @Inject constructor(private val dispatcher: MainDispatcher) : Store() {

  val repos: LiveData<List<Repo>> = dispatcher.onRefreshRepo
      .map { it.data }
      .observeOn(AndroidSchedulers.mainThread())
      .toLiveData()

  val repoReadmeUrl: LiveData<String> = dispatcher.onShowRepoReadme
      .map { it.data }
      .observeOn(AndroidSchedulers.mainThread())
      .toLiveData()

}
