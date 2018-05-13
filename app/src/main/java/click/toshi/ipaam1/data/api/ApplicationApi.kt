package click.toshi.ipaam1.data.api

import android.support.annotation.CheckResult
import click.toshi.ipaam1.data.api.response.Response
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApplicationApi {
    @GET("sessionize/all.json")
    @CheckResult
    fun getSessions(): Deferred<Response>
}
