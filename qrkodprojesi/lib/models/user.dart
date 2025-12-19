// lib/models/user.dart (QR Kod Üretme Uygulaması İçin)
class User {
  final int id;

  final String company;
  final String username;

  User({
    required this.id,
  
    required this.company,
    required this.username,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'] as int,
     
      company: json['company'] as String,
      username: json['username'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
   
      'company': company,
      'username': username,
    };
  }
}
