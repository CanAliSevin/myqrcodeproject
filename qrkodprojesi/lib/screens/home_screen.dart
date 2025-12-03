// lib/screens/home_screen.dart (QR Üretme Uygulaması İçin - Şirket Adı Güncellendi)
import 'package:flutter/material.dart';
import 'package:qr_flutter/qr_flutter.dart';
import 'dart:async';
import 'package:provider/provider.dart';
import 'package:qrkodprojesi/providers/user_provider.dart';
import 'package:qrkodprojesi/screens/login_screen.dart';
import 'package:uuid/uuid.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _qrData = "QR Kodu Yükleniyor...";
  Timer? _qrUpdateTimer;
  Timer? _countdownTimer;
  int _countdown = 7;
  final Uuid _uuid = const Uuid();

  static const String APP_QR_PREFIX = "MYQRAPP_SECURE_";

  @override
  void initState() {
    super.initState();
    _generateAndStartTimers();
  }

  Future<void> _generateAndStartTimers() async {
    final userProvider = Provider.of<UserProvider>(context, listen: false);
    final currentUser = userProvider.currentUser;

    if (currentUser == null) {
      setState(() {
        _qrData = "Kullanıcı bilgisi bulunamadı. Lütfen giriş yapın.";
      });
      return;
    }

    // QR kod içeriğini oluştururken currentUser.company'yi kullanıyoruz.
    // Bu, LoginScreen'de 'Mantis Technology' olarak ayarlanacak.
    final String uniqueQrId = _uuid.v4();
    final String qrContent =
        "${APP_QR_PREFIX}${currentUser.company}_${uniqueQrId}_${DateTime.now().toIso8601String()}";

    setState(() {
      _qrData = qrContent;
      _countdown = 7;
    });

    _countdownTimer?.cancel();
    _qrUpdateTimer?.cancel();

    _countdownTimer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        if (_countdown > 0) {
          _countdown--;
        }
      });
    });

    _qrUpdateTimer = Timer.periodic(const Duration(seconds: 7), (timer) {
      _generateAndStartTimers();
    });
  }

  @override
  void dispose() {
    _qrUpdateTimer?.cancel();
    _countdownTimer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final userProvider = Provider.of<UserProvider>(context);
    final currentUser = userProvider.currentUser;

    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'QR GENERATER MANTİS',
          style: TextStyle(fontWeight: FontWeight.w400, letterSpacing: 5),
        ),
        centerTitle: true,
        actions: [
          IconButton(
            icon: const Icon(Icons.logout_outlined),
            onPressed: () {
              Provider.of<UserProvider>(context, listen: false).clearUser();
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (context) => const LoginScreen()),
              );
            },
          ),
        ],
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              decoration: const BoxDecoration(
                color: Colors.blue,
                shape: BoxShape.rectangle,
                gradient: LinearGradient(
                    colors: [Colors.blueAccent, Colors.lightBlue]),
                boxShadow: [
                  BoxShadow(
                    color: Colors.blue,
                    blurRadius: 15.0,
                    offset: Offset(0, 2),
                  ),
                ],
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'MANTİS',
                    style: TextStyle(
                      letterSpacing: 5,
                      color: Colors.white,
                      fontSize: 24,
                    ),
                  ),
                  const SizedBox(height: 8),
                  const Text(
                    'Sistem Menüsü',
                    style: TextStyle(
                      color: Colors.white70,
                      fontSize: 16,
                    ),
                  ),
                  const Spacer(),
                  Text(
                    'Kullanıcı: ${currentUser?.username ?? 'Misafir'}',
                    style: const TextStyle(color: Colors.white, fontSize: 14),
                  ),
                  // Şirket adını burada da güncelleyelim
                  Text(
                    'Şirket: ${currentUser?.company ?? 'Bilinmiyor'}',
                    style: const TextStyle(color: Colors.white, fontSize: 14),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'QR kodun yenilenmesine son\n$_countdown\nsaniye',
              textAlign: TextAlign.center,
              style: const TextStyle(fontSize: 14, color: Colors.grey),
            ),
            const SizedBox(height: 20),
            QrImageView(
              data: _qrData,
              version: QrVersions.auto,
              size: 280.0,
              gapless: false,
            ),
            const SizedBox(height: 20),
            const Center(
              child: Text(
                "Lütfen QR kodu okuyucu ekrana çeviriniz",
                style: TextStyle(
                    decoration: TextDecoration.underline, fontSize: 15),
              ),
            )
          ],
        ),
      ),
    );
  }
}
