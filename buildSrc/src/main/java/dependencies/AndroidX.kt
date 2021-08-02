package dependencies

object AndroidX {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"


    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val ui_tooling = "androidx.ui:ui-tooling:${Versions.androidx_ui}"
    const val nav_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_component}"
    const val material = "com.google.android.material:material:${Versions.material}"


    const val nav_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_component}"


    const val rxjava ="io.reactivex.rxjava3:rxjava:${Versions.rxjava}"

    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"

    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"



    const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"


    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"
}