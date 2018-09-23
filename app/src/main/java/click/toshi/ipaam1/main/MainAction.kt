package click.toshi.ipaam1.main

import click.toshi.ipaam1.data.entity.Repo
import click.toshi.ipaam1.flux.Action

sealed class MainAction<out T> : Action<T> {
  class RefreshRepo(override val data: List<Repo>) : MainAction<List<Repo>>()
  class ShowRepoReadme(override val data: String) : MainAction<String>()
}
