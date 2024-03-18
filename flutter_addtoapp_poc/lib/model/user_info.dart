class UserInfo {
  final String name;
  final int age;
  UserInfo(this.name, this.age);
  UserInfo.fromJson(Map<String, dynamic> json)
      : name = json['name'] as String,
        age = json['age'] as int;
  Map<String, dynamic> toJson() => {
    'name': name,
    'age': age,
  };
}