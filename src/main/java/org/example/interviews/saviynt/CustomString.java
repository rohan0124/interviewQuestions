package org.example.saviynt;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class CustomString {
    private String strInternal; // Compressed form like "a2b3c1"

    CustomString(String s) {
        strInternal = compress(s); // Convert input to compressed form
    }

    public Iterator<Character> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<Character> {
        private int index = 0; // Tracks position in `strInternal`
        private char currentChar; // Stores current character
        private int remainingCount = 0; // How many times `currentChar` is left

        public CustomIterator() {
            if (!strInternal.isEmpty()) {
                currentChar = strInternal.charAt(0);
                remainingCount = strInternal.charAt(1) - '0'; // Convert char count to int
            }
        }

        @Override
        public boolean hasNext() {
            return index < strInternal.length() || remainingCount > 0;
        }

        @Override
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (remainingCount == 0) { // Move to next character if count is 0
                index += 2;
                if (index < strInternal.length()) {
                    currentChar = strInternal.charAt(index);
                    remainingCount = strInternal.charAt(index + 1) - '0';
                }
            }

            remainingCount--;
            return currentChar;
        }
    }

    private String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        sb.append(s.charAt(s.length() - 1)).append(count);
        return sb.toString();
    }

    public static void main(String[] args) {
        CustomString s = new CustomString("aabbbc");

        for (Iterator<Character> it = s.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

}

