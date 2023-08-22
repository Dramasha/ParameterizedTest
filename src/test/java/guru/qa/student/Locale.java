package guru.qa.student;

public enum Locale {
    ENGLISH("English"),
    RUSSIAN("Русский");

    private final String lang;
    Locale(String value) {
        this.lang = value;
    }
    public String getLang() {
        return lang;
    }
}