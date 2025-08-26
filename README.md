# Material Theme Builder Compose Export

## Basics

This archive contains a number of files defining a Material 3 theme:

* ui/theme/Color.kt        - contains all colors used by your theme
* ui/theme/Theme.kt        - assigns those colors to roles
*                          - includes code to instantiate an app theme

In your project, you can copy the two directories to /app/src/main/java/<your_package>/.

The files attach a default package name of com.example.ui.theme. You will want
to change that to your actual package name for your app.

Please note that in Android Studio, the Android project view groups some directories together.
For example, /ui/theme will appear in the ui as ui.theme .
## Dependencies
To use the theme, you will need to add the following dependencies to your build.gradle file:

```gradle
implementation "androidx.compose.material3:material3:1.2.0"
implementation "androidx.compose.material3:material3-window-size-class:1.2.0"
implementation "androidx.compose.ui:ui-tooling-preview:1.4.3"
debugImplementation "androidx.compose.ui:ui-tooling:1.4.3"
``` 
## Hilt Dependency Injection
If you are using Hilt for dependency injection, you may want to add the following dependencies to