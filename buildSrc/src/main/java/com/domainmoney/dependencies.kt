@file:Suppress("unused", "MemberVisibilityCanBePrivate", "ClassName")

package com.domainmoney

object app {
    const val applicationId = "com.domainmoney.interview"
    const val buildTools = "28.0.3"

    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = compileSdk
    const val versionCode = 3
    const val versionName = "0.1"
}

object versions {
    const val appcompat = "1.3.0"
    const val accompanist = "0.13.0"
    const val compose = "1.0.0-rc01"
    const val composeUiTooling = "1.0.0-beta09"
    const val composeActivity = "1.3.0-rc01"
    const val composeNavigation = "2.4.0-alpha01"
    const val coroutines = "1.5.0"
    const val gradle = "4.2.1"
    const val hilt = "2.37"
    const val kotlin = "1.5.10"
    const val material = "1.3.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val moshi = "1.12.0"
    const val room = "2.3.0"
    const val timber = "4.7.1"
}

object plugin {
    object android {
        const val gradle = "com.android.tools.build:gradle:${versions.gradle}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${versions.hilt}"
    }

    object kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
    }
}

object libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"

    object androidx {
        const val appCompat = "androidx.appcompat:appcompat:${versions.appcompat}"

        object room {
            const val room = "androidx.room:room-runtime:${versions.room}"
            const val ktx = "androidx.room:room-ktx:${versions.room}"
            const val compiler = "androidx.room:room-compiler:${versions.room}"
        }
    }

    object google {
        const val material = "com.google.android.material:material:${versions.material}"
    }

    const val timber = "com.jakewharton.timber:timber:${versions.timber}"

    object compose {
        const val ui = "androidx.compose.ui:ui:${versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${versions.composeUiTooling}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${versions.compose}"
        const val activity = "androidx.activity:activity-compose:${versions.composeActivity}"
        const val foundation = "androidx.compose.foundation:foundation:${versions.compose}"
        const val material = "androidx.compose.material:material:${versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${versions.composeNavigation}"

        object accompanist {
            const val insets = "com.google.accompanist:accompanist-insets:${versions.accompanist}"
            const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${versions.accompanist}"
            const val coil = "com.google.accompanist:accompanist-coil:${versions.accompanist}"
        }
    }

    object coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"
    }

    object hilt {
        const val android = "com.google.dagger:hilt-android:${versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${versions.hilt}"
    }

    object networking {
        object retrofit {
            const val retrofit = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
            const val moshi = "com.squareup.retrofit2:converter-moshi:${versions.retrofit}"
        }

        object okhttp {
            const val okhttp = "com.squareup.okhttp3:okhttp:${versions.okhttp}"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${versions.okhttp}"
        }
    }

    object moshi {
        const val moshi = "com.squareup.moshi:moshi:${versions.moshi}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${versions.moshi}"
    }
}
