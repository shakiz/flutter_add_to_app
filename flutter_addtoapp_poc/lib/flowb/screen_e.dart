import 'package:flutter/material.dart';

class ScreenE extends StatefulWidget {
  const ScreenE({super.key});

  @override
  State<ScreenE> createState() => _ScreenEState();
}

class _ScreenEState extends State<ScreenE> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: ListView(
          children: [
            const Text("Screen E"),
            TextButton(onPressed: () {}, child: const Text("Launch Screen F"))
          ],
        ),
      ),
    );
  }
}
