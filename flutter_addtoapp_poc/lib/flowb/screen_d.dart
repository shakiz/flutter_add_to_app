import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../model/user_info.dart';

const MethodChannel platformChannel = MethodChannel('get_data_channel');

class ScreenD extends StatefulWidget {
  const ScreenD({super.key});

  @override
  State<ScreenD> createState() => _ScreenDState();
}

class _ScreenDState extends State<ScreenD> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: ListView(
          children: [
            const Text("Screen D"),
            TextButton(onPressed: () {}, child: const Text("Launch Screen E")),
            TextButton(onPressed: () {
              fetchDataFromNative();
            }, child: const Text("Get Data")),
            TextButton(onPressed: () {
              fetchUserDataFromNative();
            }, child: const Text("Get User Data")),
            TextButton(onPressed: () {
              notifyClosed();
            }, child: const Text("Exit"))
          ],
        ),
      ),
    );
  }

  void fetchDataFromNative() async {
    try {
      final String result = await platformChannel.invokeMethod('getDataFromNative');
      print('Result from Native: $result');
    } on PlatformException catch (e) {
      print('Error: ${e.message}');
    }
  }

  void fetchUserDataFromNative() async {
    try {
      final String result = await platformChannel.invokeMethod('getUserInfoFromNative');
      final userMap = jsonDecode(result) as Map<String, dynamic>;
      final user = UserInfo.fromJson(userMap);
      print('Howdy, ${user.name}!');
      print('We sent the verification link to ${user.age}.');
      print('Result from Native: $result');
    } on PlatformException catch (e) {
      print('Error: ${e.message}');
    }
  }

  void notifyClosed() async {
    try {
      final String result = await platformChannel.invokeMethod('exitFlow');
      SystemNavigator.pop();
      print('Closing Flow');
    } on PlatformException catch (e) {
      print('Error: ${e.message}');
    }
  }
}
