< 2023 - Template Codebase >
===============


<img width="625" alt="Screenshot 2023-02-01 at 12 27 48" src="https://user-images.githubusercontent.com/16299826/215922538-3325c38a-a9a2-401b-9534-fbdda0a7545f.png">


This is a template codebase in both Kotlin Traditional View implementation and Compose (WIP) 

Initial structure is made up of nested navigation graphs, 3 initial bottom navigation bar tabs, a basic initial Dependency Injection structure via Hilt and further examples of the Jetpack libraries that are included. 

Home tab includes a Data-bound Recyclerview (`RecyclerView`) of SpaceX launches using the SpaceX API v5. 


- `extra` folder includes all utilities and extensions, as well as convenience classes and bindings / binding adapters.
- **<ins>The UI layer</ins>**
  - `HomeViewModel` and `HomeFragment` make use of, and observe `StateFlow` and `LiveData` for management of the UI states and adapter management.
  - Data-bound list: <ins>Implementation favors data-bound RecyclerView</ins> which is built with data bindings (and Advanced Data Binding concept `BR.item`) that are setup by the layout (see below attachment) and `HomeViewModel`, marked by config comment. `stocks` LiveData contains the item list, which can also be a sealed type that enables expanding with different types of UI containers within layout provider of `ViewModel` for different item types. The actual bindings' implementations all reside under `extra/recyclerview`
  - DiffUtil is also utilized under the listview, which by default enables item change animations, crossfade and even item swap animations.
  ![](bindings.png)


Getting Started
---------------
### Run
Build system uses Gradle and build scripts are written in `Gradle Kotlin DSL`

For building or installing, use the `gradlew build` and `gradlew install` after importing and synchronizing the project. 

### Tests (Deprecated)
Right click the test (unit test) folder under `xxx` to run all the unit tests inside `TestHilt`.

or 

use command `./gradlew testDebugUnitTest --tests "net.sdfgsdfg.domain.TestHilt"`

![](test.png)


...check the test results: 
![](testresults.png)

Dependencies
--------------
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
    asynchronous tasks for optimal execution.
  * [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Fragment][34] - A basic unit of composable UI.
  * [Hilt][92]: for [dependency injection][93]
  * [Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks
  * [Moshi][166] for parsing the data layer POJOs

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[18]: https://developer.android.com/topic/libraries/architecture/workmanager
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[90]: https://bumptech.github.io/glide/
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/hilt-android
[93]: https://developer.android.com/training/dependency-injection
[166]: https://github.com/square/moshi

Contact
-------
Kaan Osmanagaoglu 

kaanixir@gmail.com
