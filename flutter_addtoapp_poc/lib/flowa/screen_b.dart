import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';

const MethodChannel platformChannel = MethodChannel('get_data_channel');

class ScreenB extends StatefulWidget {
  const ScreenB({super.key});

  @override
  State<ScreenB> createState() => _ScreenBState();
}

class _ScreenBState extends State<ScreenB> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: ListView(
          children: [
            const Text("Screen B"),
            TextButton(
                onPressed: () {
                  context.goNamed('screen_c');
                },
                child: const Text("Launch Screen C")),
            TextButton(onPressed: () {
              fetchDataFromNative();
            }, child: const Text("Get Data"))
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
}
