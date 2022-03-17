# Android Documentation
## Build a local Android Library file (.aar file)
In the terminal run the following command
- `./gradlew assemble` To build both .aar for Debug and Release.

As well, you can run
- `./gradlew assembleDebug` To build for Debug.
- `./gradlew assembleRelease` To build for Release.

The .aar files will be located in `<nytimes-mobile-core-path>/build/outputs/aar/`.
