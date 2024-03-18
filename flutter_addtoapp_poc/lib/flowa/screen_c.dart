import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class ScreenC extends StatefulWidget {
  const ScreenC({super.key});

  @override
  State<ScreenC> createState() => _ScreenBState();
}

class _ScreenBState extends State<ScreenC> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: ListView(
          children: [
            const Text("Screen C"),
            TextButton(
                onPressed: () {
                  context.goNamed('/');
                },
                child: const Text("Exit Flow A"))
          ],
        ),
      ),
    );
  }
}
