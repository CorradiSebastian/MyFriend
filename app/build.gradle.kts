plugins {
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.sebastiancorradi.myfriend"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sebastiancorradi.myfriend"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //androidX service extension
    implementation("androidx.core:core:1.12.0")

    //navigation
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")


    implementation("androidx.hilt:hilt-work:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //lifecycleScope for the service to run corroutines
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //constraint layout
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Glide
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    //maps
    implementation ("com.google.maps.android:maps-compose:2.11.4")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")

    implementation ("androidx.navigation:navigation-compose:2.7.7")

    //tests
    // Required -- JUnit 4 framework

    val jUnitVersion = "4.13.2"
    val androidXTestVersion = "1.5.0"
    val mockitoVersion = "4.0.0"
    val mockitoKotlinVersion = "4.0.0"
    val mockkVersion = "1.13.1"
    testImplementation ("junit:junit:$jUnitVersion")
    // Optional -- Robolectric environment
    testImplementation ("androidx.test:core:$androidXTestVersion")
    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:$mockitoVersion")
    // Optional -- mockito-kotlin
    testImplementation ("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
    // Optional -- Mockk framework
    testImplementation ("io.mockk:mockk:$mockkVersion")

    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.47")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.47")

    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")


    //ROOM
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
}