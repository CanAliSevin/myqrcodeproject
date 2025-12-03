// lib/screens/login_screen.dart (QR Kod Üretme Uygulaması İçin - Şirket Adı Güncellendi)
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:qrkodprojesi/screens/home_screen.dart';
import 'package:qrkodprojesi/models/user.dart';
import 'package:qrkodprojesi/providers/user_provider.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  bool _isLoading = false;

  static const String ADMIN_USERNAME = 'testuser';
  static const String ADMIN_PASSWORD = 'testpass';
  static const String ADMIN_COMPANY =
      'Mantis Technology'; // <<< ŞİRKET ADI BURADA GÜNCELLENDİ

  void _showSnackBar(String message, Color color) {
    if (mounted) {
      ScaffoldMessenger.of(context).hideCurrentSnackBar();
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text(message),
          backgroundColor: color,
          duration: const Duration(seconds: 3),
        ),
      );
    }
  }

  Future<void> _login() async {
    setState(() {
      _isLoading = true;
    });

    final String username = _usernameController.text;
    final String password = _passwordController.text;

    if (username.isEmpty || password.isEmpty) {
      _showSnackBar('Kullanıcı adı ve şifre boş bırakılamaz.', Colors.red);
      setState(() {
        _isLoading = false;
      });
      return;
    }

    await Future.delayed(const Duration(milliseconds: 500));

    if (username == ADMIN_USERNAME && password == ADMIN_PASSWORD) {
      _showSnackBar('Admin Girişi Başarılı!', Colors.green);

      // UserProvider'a sahte bir User nesnesi set et
      final User adminUser = User(
        id: 1,
        username: ADMIN_USERNAME,
        company: ADMIN_COMPANY, // Buradaki şirket adı kullanılacak
      );
      Provider.of<UserProvider>(context, listen: false).setUser(adminUser);

      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => const HomeScreen()),
      );
    } else {
      _showSnackBar(
          'Giriş Başarısız: Yanlış kullanıcı adı veya şifre.', Colors.red);
    }

    setState(() {
      _isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'QR GENERATER MANTİS',
          style: TextStyle(letterSpacing: 5, fontWeight: FontWeight.w400),
        ),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            TextField(
              cursorColor: Colors.black,
              controller: _usernameController,
              decoration: const InputDecoration(
                labelText: 'Kullanıcı Adı',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.person),
              ),
            ),
            const SizedBox(height: 20),
            TextField(
              controller: _passwordController,
              obscureText: true,
              decoration: const InputDecoration(
                labelText: 'Şifre',
                border: OutlineInputBorder(),
                prefixIcon: Icon(Icons.lock),
              ),
            ),
            const SizedBox(height: 30),
            ElevatedButton(
              onPressed: _isLoading ? null : _login,
              style: ElevatedButton.styleFrom(
                minimumSize: const Size(double.infinity, 50),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10),
                ),
              ),
              child: _isLoading
                  ? const CircularProgressIndicator(color: Colors.white)
                  : const Text(
                      'Giriş Yap',
                      style: TextStyle(fontSize: 18, color: Colors.blue),
                    ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _usernameController.dispose();
    _passwordController.dispose();
    super.dispose();
  }
}
