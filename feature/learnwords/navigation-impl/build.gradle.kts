plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)

}

android {
    namespace = "iv.vas.learnwords.navigation.impl"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:learnwords:data"))
    implementation(project(":feature:learnwords:ui"))
    implementation(libs.bundles.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(project(":feature:learnwords:navigation-api"))
    ksp(libs.bundles.hilt.compiler)
    implementation(libs.bundles.navigation)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.hilt)
}
hilt {
    enableAggregatingTask = false
}
