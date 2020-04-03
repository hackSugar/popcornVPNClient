package com.tejasmehta.popcorn_vpn;

import android.util.Log;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "com.tejasmehta.popcorn_vpn/vpnManager";
  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
            .setMethodCallHandler(
                    (call, result) -> {
                      if (call.method.equals("connect")) {
                          String arg1 = call.argument("MSG");
                          int arg2 = call.argument("MSG2");
                        int connect = connect(arg1, arg2);
                        result.success(connect);
                      } else {
                        result.notImplemented();
                      }
                    }
            );
  }
  private int connect(String MSG, int MSG2) {
      Log.i("ARG1", MSG);
      Log.i("ARG2", String.valueOf(MSG2));
      return 0;
  }
}
