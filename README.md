# VivyDoctors

## Technical choices
- **Android JetPack:** Android Jetpack is a set of components, tools and guidance to make great Android apps. For more details, visit [HERE](https://developer.android.com/jetpack)
- **AndroidX:** It removes all the dependencies of the Support Library means now there will be no impact or no need to change the versions according to the SDK versions. For more details, visit [HERE](https://developer.android.com/jetpack/androidx)
- **Language:** This app is developed on 100% Kotlin (v1.3.61) which is one of the strongest recommenced by JetPack to use Kotlin. For more details, visit [HERE](https://developer.android.com/kotlin/ktx.html)
- **MvvM:** The architecture of this app is view model. This is the latest and recommended architecture for android. MVVM uses data binding and is therefore a more event driven architecture. It can map many views to one view model. Like many fragments or activities can use one viewmodel and also the same values without destroying/loosing them. In MVVM, the view model has no reference to the view. Testing becomes easy after using it. For more details, visit [HERE](https://developer.android.com/topic/libraries/architecture/viewmodel).
- **LiveData:** LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state. For more details, visit [HERE](https://developer.android.com/topic/libraries/architecture/livedata).
- **Paging:** The Paging Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources. For more details, visit [HERE](https://developer.android.com/topic/libraries/architecture/paging/).
- **Co-Routines:** This is a concurrency design pattern that we can use on Android to simplify code that executes asynchronously. 
- **Glide:** To handle the Image loading
- **Retrofit:** A type-safe HTTP client. It turns the HTTP API into a Java/Kotlin interface. 
- **OkHttp:** An HTTP & HTTP/2 client for Android 
- **Timber:** To show the logging in debug mode
- **Material Design:** To smooth the UI with material style
- **Dagger2:** Dependency injection framework 
- **JUnits & Mockito:** For unit testing
- **Espresso:** For ui and integration testing
