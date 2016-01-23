# Android GCM - only a sample

Android GCM - Google Cloud Message
This is a simple example of how to use GCM in Android applications. My motivation is help users to put this feature in your applications, because in some topics, I saw problems in how to implement it, but in general is very easy.

I will suppose that you have your bundle id application (in AndroidManifest.xml), in my example I will use:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.brunogabriel.nativegcm">
```

### Register your account
First it is necessary to create and enable GCM by making a project, steps:

1. With your package android in hands:
	- Enter in [Google Developers Console] (http://console.developers.google.com) and create your own application, in my case named Native GCM.
	
2. Do not forgot to save your project number, we will use it in Android Application directly.

3. Create your credentials:
    - When I made it, I did not saved an IP Address, only empty this field to test.
    - So now, write your credentials key.

### Configuration: Android application
In Android project:

1. First, please, read as soon as possible all comments (small quantity) in @.java files, because some contains tips, e.g.  

