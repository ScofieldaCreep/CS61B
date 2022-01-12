import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
//        if (word.length() == 1 || word.length() == 0) {
//            return true;
//        }
//
//        Deque<Character> target = wordToDeque(word);
//        while (true) {
//            if (target.removeFirst() != target.removeLast()) {
//                return false;
//            }
//            if (target.isEmpty() || target.size() == 1) {
//                return true;
//            }
//        }

        // Recursive
        Deque<Character> target = wordToDeque(word);
        return isPalindromeHelper(target);
    }

    private boolean isPalindromeHelper(Deque target) {
        if (target.size() <= 1) {
            return true;
        } else if (target.removeFirst() != target.removeLast()) {
            return false;
        }
        return isPalindromeHelper(target);
    }

    /* return true if the word is a palindrome according to the character comparison test
    provided by the CharacterComparator passed in as argument cc. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> target = wordToDeque(word);
        return isPalindromeHelper(target, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> target, CharacterComparator cc) {
        if (target.size() <= 1) {
            return true;
        } else if (!cc.equalChars((char)target.removeFirst(), (char)target.removeLast())) {
            return false;
        }
        return isPalindromeHelper(target, cc);
    }

}