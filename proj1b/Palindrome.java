public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> charlist = new ArrayDeque<Character>();
        for (int i = 0; i<word.length(); i++) {
            char c = word.charAt(i);
            charlist.addLast(c);
        }
        return charlist;
    }
    public boolean isPalindrome(String word) {
        if (word == null) return false;
        if (word.length() == 1 || word.isEmpty()) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        int len = d.size();
        for (int i = 0, j = len -1; i < j; i++, j--) {
            if (d.get(i) != d.get(j)) {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) return false;
        if (word.length() == 1 || word.isEmpty()) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        int len = d.size();
        for (int i = 0, j = len -1; i < j; i++, j--) {
            if (!cc.equalChars(d.get(i), d.get(j))) {
                return false;
            }
        }
        return true;
    }
}
