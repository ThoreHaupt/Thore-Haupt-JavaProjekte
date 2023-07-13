import 'dart:async';

import 'package:mailgw_client/mailgw_client.dart';

void main() async {
  GWAccount account = await MailGw.register();
  print('Send a message to the following account: $account');
  late StreamSubscription subscription;
  subscription = account.messages.listen((e) async {
    print('Listened to message with id: $e');
    if (e.hasAttachments) {
      print('Message has following attachments:');
      e.attachments.forEach((a) => print('- $a'));
    }
    await subscription.cancel();
  });
}