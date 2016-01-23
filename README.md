# Android GCM - only a sample

Android GCM - Google Cloud Message
This is a simple example of how to use GCM in Android applications. My motivation is help users to put this feature in your applications, because in some topics, I saw problems in how to implement it, but in general is very easy.

I will suppose that you have your bundle id application (in AndroidManifest.xml), in my example I will use:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.brunogabriel.nativegcm">
```

## Register your account
> First it is necessary to create and enable GCM by making a project, steps:
