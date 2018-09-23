package click.toshi.ipaam1.flux

interface Action<out T> {
  val data: T
}
