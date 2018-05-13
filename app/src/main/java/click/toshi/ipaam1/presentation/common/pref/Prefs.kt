package click.toshi.ipaam1.presentation.common.pref

import com.chibatching.kotpref.KotprefModel
import click.toshi.ipaam1.R
import click.toshi.ipaam1.util.ext.bool

object Prefs : KotprefModel() {
    public override val kotprefName: String = "ipaam1_prefs"

    var enableNotification: Boolean by booleanPref(
            default = true,
            key = R.string.pref_key_enable_notification
    )
}
