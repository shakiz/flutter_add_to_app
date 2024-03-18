import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_addtoapp_poc/flowa/screen_a.dart';
import 'package:flutter_addtoapp_poc/flowa/screen_b.dart';
import 'package:flutter_addtoapp_poc/flowb/screen_d.dart';
import 'package:flutter_addtoapp_poc/flowb/screen_e.dart';
import 'package:flutter_addtoapp_poc/flowb/screen_f.dart';
import 'package:go_router/go_router.dart';

import 'flowa/screen_c.dart';

final MethodChannel platformChannel = MethodChannel('flutter_addtoapp_poc');

void fetchDataFromNative() async {
  try {
    final String result = await platformChannel.invokeMethod('getDataFromNative');
    print('Result from Native: $result');
  } on PlatformException catch (e) {
    print('Error: ${e.message}');
  }
}

void main() => runApp(const MyApp());

final GoRouter _router = GoRouter(
  initialLocation: "/",
  routes: <RouteBase>[
    GoRoute(
      path: '/',
      name: "/",
      builder: (BuildContext context, GoRouterState state) {
        return const ScreenA();
      },
      routes: <RouteBase>[
        GoRoute(
            path: 'screen_b',
            name: 'screen_b',
            builder: (BuildContext context, GoRouterState state) {
              return const ScreenB();
            },
            routes: <RouteBase>[
              GoRoute(
                path: 'screen_c',
                name: 'screen_c',
                builder: (BuildContext context, GoRouterState state) {
                  return const ScreenC();
                },
              ),
            ]
        ),
      ],
    ),
    GoRoute(
      path: '/screen_d',
      name: 'screen_d',
      builder: (BuildContext context, GoRouterState state) {
        return const ScreenD();
      },
      routes: <RouteBase>[
        GoRoute(
          path: 'screen_e',
          name: 'screen_e',
          builder: (BuildContext context, GoRouterState state) {
            return const ScreenE();
          },
        ),
        GoRoute(
          path: 'screen_f',
          name: 'screen_f',
          builder: (BuildContext context, GoRouterState state) {
            return const ScreenF();
          },
        ),
      ],
    ),
  ],
);


class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      routerConfig: _router,
    );
  }
}
