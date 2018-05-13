package click.toshi.ipaam1.data.db

import android.support.annotation.CheckResult
import click.toshi.ipaam1.model.Session
import io.reactivex.Flowable
import io.reactivex.Single

interface FavoriteDatabase {

    @get:CheckResult val favorites: Flowable<List<Int>>

    @CheckResult fun favorite(session: Session): Single<Boolean>
}
