public class StringDemo {
    public static void main(String[] args) {
        String str1 = "  Hello World  ";
        String str2 = "hello world";
        String str3 = "Java Programming";
        
        System.out.println("1. Basic Information");
        System.out.println("Original String: '" + str1 + "'");
        System.out.println("Length: " + str1.length());
        
        System.out.println("Is Empty: " + str1.isEmpty());
        
        System.out.println("Char at index 2: " + str1.charAt(2));

        System.out.println("\n2. Comparison");
        String trimmed = str1.trim();
        System.out.println("Equals (case sensitive): " + trimmed.equals(str2));
        
        System.out.println("CompareTo result: " + trimmed.compareTo(str3));

        System.out.println("\n3. Searching");
        System.out.println("Contains 'World': " + trimmed.contains("World"));
        
        System.out.println("Index of 'o': " + trimmed.indexOf('o'));
        
        System.out.println("Last Index of 'o': " + trimmed.lastIndexOf('o'));
        
        System.out.println("Starts with 'Hel': " + trimmed.startsWith("Hel"));
        System.out.println("Ends with 'rld': " + trimmed.endsWith("rld"));

        System.out.println("\n4. Modification & Extraction");
        System.out.println("Substring (0 to 5): " + trimmed.substring(0, 5));
        
        System.out.println("Lowercase: " + trimmed.toLowerCase());
        
        System.out.println("Trimmed version: '" + trimmed + "'");
        
        System.out.println("Replace 'World' with 'Java': " + trimmed.replace("World", "Java"));

        System.out.println("\n5. Advanced Op.erations");
        System.out.println("Matches [a-z ]+ (lowercase/space): " + str2.matches("[a-z ]+"));
        
        String[] words = str3.split(" ");
        System.out.println("Split result 1: " + words[0]);
        System.out.println("Split result 2: " + words[1]);
        
        String joined = String.join("-", "2023", "10", "25");
        System.out.println("Join result: " + joined);
        
        System.out.println("toString(): " + str3.toString());

        int number = 100;
        String numStr = String.valueOf(number);
        System.out.println("valueOf(100) + 50: " + (numStr + 50));
    }
}