package dependencies

object Test {
    const val jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter}"
    const val jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit_jupiter}"
    const val junit4 = "androidx.test.ext:junit:${Versions.junit4}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val test_core = "androidx.test:core:${Versions.test_core}"
    const val test_runner = "androidx.test:runner:${Versions.test_core}"
    const val core_testing = "android.arch.core:core-testing:${Versions.core_testing}"
    const val mock_web_server = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val truth_google = "com.google.truth:truth:${Versions.truth}"

    const val hilt_testing= "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val hilt_android_compiler= "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

}