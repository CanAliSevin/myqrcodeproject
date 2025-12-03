// lib/providers/user_provider.dart
import 'package:flutter/material.dart';
import 'package:qrkodprojesi/models/user.dart'; // User modelinizi import edin

class UserProvider extends ChangeNotifier {
  User? _currentUser; // Oturum açmış kullanıcının bilgisi

  User? get currentUser => _currentUser;

  // Kullanıcı oturum açtığında veya güncellendiğinde bu metodu çağırın
  void setUser(User user) {
    _currentUser = user;
    notifyListeners(); // Dinleyicilere (widget'lara) değişiklik olduğunu bildir
  }

  // Kullanıcı oturumu kapattığında bu metodu çağırın
  void clearUser() {
    _currentUser = null;
    notifyListeners(); // Dinleyicilere değişiklik olduğunu bildir
  }

  // Kullanıcının oturum açıp açmadığını kontrol etmek için
  bool get isAuthenticated => _currentUser != null;
}
