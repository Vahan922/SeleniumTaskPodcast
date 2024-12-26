public enum Credentials {
    TEST_USER("Vahan.musayelyan+344@podcastle.ai", "123456", "https://qa.podcastle.ai");

    private final String username;
    private final String password;
    private final String baseUrl;

    Credentials(String username, String password, String baseUrl) {
        this.username = username;
        this.password = password;
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}