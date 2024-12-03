plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    macosArm64 {
        binaries {
            executable {
                entryPoint = "nativeEntryPoint"
            }
        }
    }

    jvm()
}
