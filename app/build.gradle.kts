plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.kapt)
    id("kotlin-kapt")  // Add KAPT plugin
}

android {
    namespace = "com.myapp.notes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.myapp.notes"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
//    packagingOptions {
//        exclude 'META-INF/atomicfu.kotlin_module'
//    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")  // Kotlin extensions and Coroutines support
    kapt("androidx.room:room-compiler:2.6.1")       // Annotation processor

    // Optional - for testing
    testImplementation("androidx.room:room-testing:2.5.0")

    // Coroutines support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
}

// KAPT configuration
kapt {
    correctErrorTypes = true
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true")
        arg("room.expandProjection", "true")
    }
}

//dependencies {
//
//    implementation ("androidx.appcompat:appcompat:$rootProject.appCompatVersion")
//    implementation ("androidx.activity:activity-ktx:$rootProject.activityVersion")
//
//    // Dependencies for working with Architecture components
//    // You'll probably have to update the version numbers in build.gradle (Project)
//
//    // Room components
//    implementation ("androidx.room:room-ktx:$rootProject.roomVersion")
////    kapt "androidx.room:room-compiler:$rootProject.roomVersion"
//    kapt(libs.androidx.room.compiler)
//    androidTestImplementation ("androidx.room:room-testing:$rootProject.roomVersion")
//
//    // Lifecycle components
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$rootProject.lifecycleVersion")
//    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.lifecycleVersion")
//    implementation ("androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycleVersion")
//
//    // Kotlin components
//    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
//    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$rootProject.coroutines")
//    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$rootProject.coroutines")
//
//    // UI
//    implementation ("androidx.constraintlayout:constraintlayout:$rootProject.constraintLayoutVersion")
//    implementation "(com.google.android.material:material:$rootProject.materialVersion")
//
//    // Testing
//    testImplementation ("junit:junit:$rootProject.junitVersion")
//    androidTestImplementation ("androidx.arch.core:core-testing:$rootProject.coreTestingVersion")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:$rootProject.espressoVersion", {
//        exclude group: 'com.android.support', module: 'support-annotations'
//    })
//    androidTestImplementation ("androidx.test.ext:junit:$rootProject.androidxJunitVersion")
//}