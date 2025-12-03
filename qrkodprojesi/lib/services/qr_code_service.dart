// lib/services/qr_code_service.dart
import 'dart:convert';
import 'package:http/http.dart' as http;

class QrCodeService {
  final String baseUrl = 'http://localhost:8090';
  Future<String> generateQrCode(String content) async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/qrcode/generate'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{'content': content}),
    );

    if (response.statusCode == 200) {
      return jsonDecode(response.body)['qrCode'];
    } else {
      String errorMessage =
          'QR kod oluşturma başarısız oldu: ${response.statusCode}';
      try {
        final errorData = jsonDecode(response.body);
        if (errorData != null &&
            errorData is Map &&
            errorData.containsKey('message')) {
          errorMessage = errorData['message'];
        }
      } catch (e) {}
      throw Exception(errorMessage);
    }
  }
}
