import 'package:flutter/material.dart';

class ScreenF extends StatefulWidget {
  const ScreenF({super.key});

  @override
  State<ScreenF> createState() => _ScreenFState();
}

class _ScreenFState extends State<ScreenF> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: ListView(
          children: [
            const Text("Screen F"),
            TextButton(onPressed: () {}, child: const Text("Exit Flow B"))
          ],
        ),
      ),
    );
  }
}
