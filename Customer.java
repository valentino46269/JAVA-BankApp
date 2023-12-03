class Customer extends User {
    private Account account;

    public Customer(String username, String password) {
        super(username, password);
        this.account = new Account();
    }

    public Account getAccount() {
        return account;
    }
}