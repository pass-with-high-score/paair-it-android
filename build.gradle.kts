// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.google.services) apply false
}

tasks.register("buildRelease") {
    group = "distribution"
    description = "Build the app for distribution (release version)"
    dependsOn(":app:assembleRelease")
}

tasks.register("buildDebug") {
    group = "build"
    description = "Builds the debug version of the app"
    dependsOn(":app:assembleDebug")
}