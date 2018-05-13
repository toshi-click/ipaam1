package click.toshi.ipaam1.util.ext

import android.content.Context
import android.databinding.ViewDataBinding

val ViewDataBinding.context: Context
    get() = root.context
