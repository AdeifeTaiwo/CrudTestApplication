plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.crudtestapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.crudtestapplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    val kotlin_version = "1.9.20"
    val compileSdkVersion = 28
    val minSdkVersion = 15
    val targetSdkVersion = 28
    val supportLibVersion = "1.2.0"
    val coreVersion = "1.3.2"
    val recyclerViewVersion = "1.2.0"
    val constraintLayoutVersion = "2.0.4"
    val materialVersion = "1.3.0"
    val lifecycleVersion = "2.8.0"
    val roomVersion = "2.6.1"
    val pagingVersion = "3.3.0"
    val retrofitVersion = "2.9.0"
    val okhttpLoggingInterceptorVersion = "4.9.0"
    val coroutines = "1.4.3"
    val version_navigation = "1.0.0"


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation ("androidx.appcompat:appcompat:$supportLibVersion")
    implementation ("androidx.recyclerview:recyclerview:$recyclerViewVersion")
    implementation ("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation ("com.google.android.material:material:$materialVersion")

    // architecture components
    implementation ("androidx.core:core-ktx:$coreVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation( "androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("androidx.paging:paging-runtime-ktx:$pagingVersion")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-paging:2.6.1")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:retrofit-mock:$retrofitVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttpLoggingInterceptorVersion")

//    // Navigation
//    implementation ("android.arch.navigation:navigation-fragment-ktx:$version_navigation")
//    implementation ("android.arch.navigation:navigation-ui-ktx:$version_navigation")


}