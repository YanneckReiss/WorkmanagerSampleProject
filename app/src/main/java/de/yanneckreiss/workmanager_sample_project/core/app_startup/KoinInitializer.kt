package de.yanneckreiss.workmanager_sample_project.core.app_startup

import android.content.Context
import androidx.startup.Initializer
import de.yanneckreiss.workmanager_sample_project.core.injection.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule

/**
 * For more insights refer to my article:
 * https://medium.com/tech-takeaways/how-to-use-the-android-jetpack-app-startup-library-e95a3702bfdf
 */
class KoinInitializer : Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {

        return startKoin {
            androidContext(context)
            modules(defaultModule + dataModule)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
