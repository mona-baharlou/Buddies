import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
}

android {
  namespace = "com.baharlou.buddies"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.baharlou.buddies"
    minSdk = 26
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
    kotlinCompilerExtensionVersion = "1.5.4"
  }

  testOptions.unitTests.apply {
    tasks.withType<Test>().all(KotlinClosure1<Test, Test>({
      apply {
        useJUnitPlatform()
        testLogging.exceptionFormat = TestExceptionFormat.FULL
        testLogging.events = setOf(
          TestLogEvent.PASSED,
          TestLogEvent.FAILED,
          TestLogEvent.STANDARD_ERROR,
          TestLogEvent.STANDARD_OUT,
          TestLogEvent.SKIPPED
        )
        testLogging.showCauses = true
        testLogging.showExceptions = true
      }
    }, this))
  }

  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.core.common)
  implementation(libs.androidx.core.testing)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  implementation(libs.cronet.embedded)

  testImplementation(libs.junit.jupiter.api)
  testImplementation(libs.jupiter.junit.jupiter.params)
  testRuntimeOnly(libs.junit.jupiter.engine)
 // testImplementation(libs.junit)

  /*
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)*/

  //androidTestImplementation(libs.ui.test.junit4)

  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}