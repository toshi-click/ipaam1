package click.toshi.ipaam1.util

import click.toshi.ipaam1.model.Lang
import java.util.Locale

fun lang(): Lang = if (Locale.JAPAN == Locale.getDefault()) {
    Lang.JA
} else {
    Lang.EN
}
