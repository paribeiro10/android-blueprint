package com.paribeiro.blueprint.functional.support.bootstrap

import android.os.StrictMode
import com.paribeiro.blueprint.BuildConfig

/** Utility meant to handle {@link StrictMode} related operations. */
object StrictModeAdmin {

    /** Procedure meant to enable and configure {@link StrictMode} Virtual Machine Policy. */
    @JvmStatic
    fun enableStrictModeVMPolicy() {
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
            .build()
        )
    }

    /** Procedure meant to enable and configure {@link StrictMode} Thread Policy. */
    @JvmStatic
    fun enableStrictModeThreadPolicy() {
        permitDiskReads {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                .build()
            )
        }
    }

    /**
     * Procedure meant to suppress Disk Read Violations that the developer is sure to be a false
     * positive.
     */
    @JvmStatic
    fun permitDiskReads(func: () -> Any) : Any = if (BuildConfig.DEBUG) {
        val oldThreadPolicy = StrictMode.getThreadPolicy()
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder(oldThreadPolicy).permitDiskReads().build()
        )
        val anyValue = func()
        StrictMode.setThreadPolicy(oldThreadPolicy)
        anyValue
    } else { func() }

}
