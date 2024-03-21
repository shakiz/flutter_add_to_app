<a name="readme-top"></a>

<br />
<div align="center">

  <h3 align="center">Flutter - Add Module Inside Native</h3>

</div>



<!-- TABLE OF CONTENTS -->
<h2>Table of Contents</h2>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#methods">Methods</a></li>
    <li><a href="#basic-mechanisms">Basic Mechanisms</a></li>
    <li><a href="#methods-to-choose">Method to Choose</a></li>
    <li><a href="#installation">Installation</a></li>
    <li><a href="#limitations">Limitations</a></li>
    <li><a href="#contributing">Contributing</a></li>
  </ol>

<!-- ABOUT THE PROJECT -->
## About The Project

Incorporating a Flutter module into an existing Android native app, also known as `Add-to-App` implementation, offers developers the flexibility to leverage Flutter's rich UI capabilities within their Android projects. This documentation provides a `step-by-step guide to various methods available for integrating a Flutter module into an Android native app`. There are several popular methods that are suggested by Flutter, and amongst them some are a good fit, others might not. The key of this solution is, embedding the Flutter module to your native application in a subproject. As the cross-platform feature of Flutter, one single Flutter module can be added to both Android and iOS projects.

<!-- Methods -->
## Methods

* Option A - Depend on the Android Archive (AAR)
* Option B - Depend on the module’s source code

<!-- Basic Mechanisms -->
## Basic Mechanisms

To launch a Flutter screen from an existing iOS/Android, we need to start a `FlutterEngine` and a `FlutterViewController/FlutterActiviy` depending on the OS.

The FlutterEngine serves as a host to the Dart VM and Flutter runtime, and the FlutterViewController/FlutterActivity attaches to a FlutterEngine to pass input events into Flutter and to display frames rendered by the FlutterEngine. The FlutterEngine may have the same lifespan as your FlutterViewController/FlutterActivity or outlive our FlutterViewController/FlutterActivity.

<!-- Methods to Choose -->
## Methods to Choose

Each of the methods provides a way to integrate the `Flutter Module into native app`. But choosing the right one might depend on the above mentioned points. 

For example, if we choose the Android Archive (AAR) way then we have to consider that it works with native by `generating an AAR file`, which leads to `issues while debugging`. Also there are times when we need to regenerate the AAR file after we made some changes into Flutter Module.

On the other hand, `module’s source code provides a much easier way to implement and debugging` is more convenient compared to AAR. Also, making changes and getting the reflection is not that much of an issue with this way.

<!-- Installation -->
### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/shakiz/flutter_add_to_app
   ```
2. Run Flutter Pub get
   ```sh
   flutter pub get
   ```
3. Run
   ```sh
   flutter run
   ```

<!-- Limitations -->
## Limitations
* `Packing Multiple Flutter Libraries:` In add-to-app setups, it's essential to understand that packing multiple Flutter libraries into an application isn't directly supported. Each Flutter module is typically integrated into a specific native app module, and there may be challenges when attempting to include multiple Flutter modules within the same native app.
* `Performance Overhead:` While Flutter provides excellent performance out-of-the-box, integrating Flutter into an existing app may introduce additional performance overhead, especially if not optimised properly.
* `Testing and Debugging:` Testing and debugging can be more challenging in an add-to-app setup, especially when dealing with issues that span both Flutter and native code.
* `Support for AndroidX:` In add-to-app setups on Android, the Flutter module only supports AndroidX applications. AndroidX is the modern Android library suite that replaces the now-deprecated Android Support Libraries.

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<br />
