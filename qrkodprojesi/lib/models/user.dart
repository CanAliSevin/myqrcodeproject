// lib/models/user.dart (QR Kod Üretme Uygulaması İçin)
class User {
  final int id;
  // final String firstName; // <<< KALDIRILDI
  // final String lastName; // <<< KALDIRILDI
  final String company;
  final String username;

  User({
    required this.id,
    // required this.firstName, // <<< KALDIRILDI
    // required this.lastName, // <<< KALDIRILDI
    required this.company,
    required this.username,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'] as int,
      // firstName: json['firstName'] as String, // <<< KALDIRILDI
      // lastName: json['lastName'] as String, // <<< KALDIRILDI
      company: json['company'] as String,
      username: json['username'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      // 'firstName': firstName, // <<< KALDIRILDI
      // 'lastName': lastName, // <<< KALDIRILDI
      'company': company,
      'username': username,
    };
  }
}
