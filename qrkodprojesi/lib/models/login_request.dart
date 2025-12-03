// lib/models/login_request.dart
class LoginRequest {
  final String username;
  final String password;

  LoginRequest({
    required this.username,
    required this.password,
  });

  // Bu nesneyi backend'e göndermek için JSON'a dönüştürür
  Map<String, dynamic> toJson() {
    return {
      'username': username,
      'password': password,
    };
  }
}
