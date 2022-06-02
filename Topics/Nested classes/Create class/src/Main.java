
class StringOperations {
    static class EngString {
        boolean isEnglishLang;
        String string;

        public EngString(boolean isEnglishLang, String string) {
            this.isEnglishLang = isEnglishLang;
            this.string = string;
        }

        public String getString() {
            return string;
        }

        public boolean isEnglish() {
            return isEnglishLang;
        }
    }
}
