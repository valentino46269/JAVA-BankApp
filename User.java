class User {
    private String username; // Nama pengguna
    public String password; // Kata sandi

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username; // Mengembalikan nama pengguna
    }

    public String getPassword() {
        return password; // Mengembalikan kata sandi
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword); // Memeriksa kecocokan kata sandi
    }
}