package click.toshi.ipaam1.test.data.api

import android.support.annotation.CheckResult
import com.squareup.moshi.Moshi
import click.toshi.ipaam1.data.api.ApplicationApi
import click.toshi.ipaam1.data.api.response.Response
import click.toshi.ipaam1.data.api.response.SponsorPlan
import click.toshi.ipaam1.data.api.response.mapper.ApplicationJsonAdapterFactory
import click.toshi.ipaam1.data.api.response.mapper.InstantAdapter
import io.reactivex.Single
import org.threeten.bp.Instant

class StubApplicationApi : ApplicationApi {
    @CheckResult override fun getSessions(): Single<Response> = Single.create { emitter ->
        val json = StubApplicationApi::class.java.classLoader.getResourceAsStream("all.json")
                .bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder()
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .add(Instant::class.java, InstantAdapter())
                .build()
        val adapter = moshi.adapter<Response>(Response::class.java)
        val response = adapter.fromJson(json)
        emitter.onSuccess(response!!)
    }

    @CheckResult override fun sponsorPlansJa(): Single<List<SponsorPlan>> {
        throw NotImplementedError()
    }

    @CheckResult override fun sponsorPlansEn(): Single<List<SponsorPlan>> {
        throw NotImplementedError()
    }
}
