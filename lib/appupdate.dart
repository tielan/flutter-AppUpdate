import 'dart:async';

import 'package:flutter/services.dart';

class Appupdate {
  static const MethodChannel _channel =
      const MethodChannel('appupdate');

  static Future<bool>  check() async {
    final bool success = await _channel.invokeMethod('check',{'url':'https://pmall.52pht.com/index.php/api?method=app.update&format=json&v=v1&'});
    return success;
  }

  static Future<int>  get getVersionCode async {
    final int vcode = await _channel.invokeMethod('getVersionCode');
    return vcode;
  }

  static Future<String> get getAppVersion async {
    final String version = await _channel.invokeMethod('getAppVersion');
    return version;
  }
}
