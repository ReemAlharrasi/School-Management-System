package utils;

// A box of small static helper methods.
// All validation rules live here in ONE place, so setters and services
// can just call these instead of copy-pasting the same checks everywhere.
// No generics are used anywhere.
public class HelperUtils {

    // A simple counter used to make unique ids (no UUID, no random).
    private static int idCounter = 1000;

    // ---------- empty checks ----------

    // true when a piece of text is null or only spaces
    public static boolean isEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }

    // true when a collection (array + how many are used) has nothing in it
    public static boolean isEmptyCollection(Object[] items, int count) {
        return items == null || count == 0;
    }

    // ---------- valid-text checks (3 overloads) ----------

    // text alone: just must not be empty
    public static boolean isValidText(String text) {
        return !isEmpty(text);
    }

    // text with a minimum length
    public static boolean isValidText(String text, int minLength) {
        return !isEmpty(text) && text.trim().length() >= minLength;
    }

    // text with a minimum and a maximum length
    public static boolean isValidText(String text, int minLength, int maxLength) {
        if (isEmpty(text)) {
            return false;
        }
        int length = text.trim().length();
        return length >= minLength && length <= maxLength;
    }

    // ---------- id generation (3 overloads) ----------

    // no prefix: gives ids like "ID1001", "ID1002" ...
    public static String generateId() {
        idCounter = idCounter + 1;
        return "ID" + idCounter;
    }

    // with a prefix: gives ids like "ENR1003", "CRS1004" ...
    public static String generateId(String prefix) {
        idCounter = idCounter + 1;
        return prefix + idCounter;
    }

    // a plain number, for the classes that keep their id as an Integer
    public static int generateNumericId() {
        idCounter = idCounter + 1;
        return idCounter;
    }

    // ---------- positive (not-negative) checks ----------
    // Used for money and years: they must be zero or more.

    public static boolean isPositive(int number) {
        return number >= 0;
    }

    public static boolean isPositive(double number) {
        return number >= 0;
    }

    // ---------- range checks (2 overloads) ----------

    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean inRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    // ---------- other small rules ----------

    // age must be between 0 and 120
    public static boolean isValidAge(Integer age) {
        return age != null && inRange(age.intValue(), 0, 120);
    }

    // phone kept as text: we only check the length here, nothing fancy
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        int length = phone.trim().length();
        return length >= 7 && length <= 15;
    }

    // phone kept as a number: must exist and be positive
    public static boolean isValidPhone(Integer phone) {
        return phone != null && phone.intValue() > 0;
    }

    // true when value equals one of the allowed words (ignoring upper/lower case)
    public static boolean isOneOf(String value, String[] allowed) {
        if (value == null || allowed == null) {
            return false;
        }
        for (int i = 0; i < allowed.length; i++) {
            if (allowed[i] != null && allowed[i].equalsIgnoreCase(value.trim())) {
                return true;
            }
        }
        return false;
    }

    // ids are stored as Integer in some classes and as String in others,
    // so this one helper compares them both the same way.
    public static boolean sameId(Object id, String text) {
        return id != null && text != null && String.valueOf(id).equals(text.trim());
    }
}
