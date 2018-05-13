package click.toshi.ipaam1.service.push

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface PushServiceBuilder {
    @ContributesAndroidInjector()
    fun contributePushService(): PushService
}
