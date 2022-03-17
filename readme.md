# nytimes-mobile-core Kotlin Multiplatform Mobile Library

## Overview

This is the library that will handle the core functionality of my NY Times for Android and iOS

## Setup

1. Create the `<nytimes-mobile-core-path>/github.properties` with the following values:
```
gpr.user=username # Your Git Hub username
gpr.key=asdfgqwerty123456 # Your nytimes-mobile-core project personal token
```
   - These values are used to publish the library in the GitHub. If you don't have these values, you can leave them for later. For now, just create the file to do local builds for Android or iOS.

2. Create the file `<nytimes-mobile-core-path>/local.properties` with following value:
```   
sdk.dir=/Your/PC/Android/SDK/Path # Android SDK absolute path
```

3. Create the file `<nytimes-mobile-core-path>/src/commonTest/kotlin/me/hectorhalpizar/TestValue.kt` with the following value.
```kotlin
package me.hectorhalpizar

const val PROJECT_PATH = "<Your nytimes-mobile-core absolute path>" // i.e. "/my/pc/projects/nytimes-mobile-core/"
```
- This file is already added into the `.gitignore` file list.

For Android Documentation go to the [docs/android.md](docs/android.md).
