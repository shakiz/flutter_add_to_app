import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class ScreenA extends StatefulWidget {
  const ScreenA({super.key});

  @override
  State<ScreenA> createState() => _ScreenAState();
}

class _ScreenAState extends State<ScreenA> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: ListView(
          children: [
            const Text("Screen A"),
            TextButton(
                onPressed: () {
                  context.goNamed('screen_b');
                },
                child: const Text("Launch Screen B"))
          ],
        ),
      ),
    );
  }
}
