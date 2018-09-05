package com.github.noconnor.reference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class Palindrome {

    public static boolean testWord(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int n = word.length();
        for (int i = 0; i < n / 2; i++) {
            if (word.charAt(i) != word.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean testSentence(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return false;
        }
        int l = 0;
        int h = sentence.length() - 1;

        String sentenceLwrCase = sentence.toLowerCase();

        while (l < h) {
            char charAtL = sentenceLwrCase.charAt(l);
            char charAtH = sentenceLwrCase.charAt(h);

            if (charAtL < 'a' || charAtL > 'z') {
                l++;
            } else if (charAtH < 'a' || charAtH > 'z') {
                h--;
            } else if (charAtL != charAtH) {
                return false;
            } else {
                l++;
                h--;
            }
        }

        return true;
    }

    // Only difference is character test
    public static boolean testSentenceUTF8(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return false;
        }
        int l = 0;
        int h = sentence.length() - 1;

        String sentenceLwrCase = sentence.toLowerCase();

        while (l < h) {
            char charAtL = sentenceLwrCase.charAt(l);
            char charAtH = sentenceLwrCase.charAt(h);

            if (!Character.isAlphabetic(charAtL)) {
                l++;
            } else if (!Character.isAlphabetic(charAtH)) {
                h--;
            } else if (charAtL != charAtH) {
                return false;
            } else {
                l++;
                h--;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        assertThat(Palindrome.testWord(null), is(false));
        assertThat(Palindrome.testWord(""), is(false));
        assertThat(Palindrome.testWord("abba"), is(true));
        assertThat(Palindrome.testWord("abdba"), is(true));
        assertThat(Palindrome.testWord("abredba"), is(false));

        assertThat(Palindrome.testSentence(null), is(false));
        assertThat(Palindrome.testSentence(""), is(false));
        assertThat(Palindrome.testSentence("too hot to hoot."), is(true));
        assertThat(Palindrome.testSentence("too hot..45%$# to hoot."), is(true));
        assertThat(Palindrome.testSentence("Hello World."), is(false));
        assertThat(Palindrome.testSentence("śćaćś"), is(true));
        assertThat(Palindrome.testSentence("śćaćśś"), is(true)); // <-- incorrect, utf8 chars skipped and not tested

        assertThat(Palindrome.testSentenceUTF8(null), is(false));
        assertThat(Palindrome.testSentenceUTF8(""), is(false));
        assertThat(Palindrome.testSentenceUTF8("too hot to hoot."), is(true));
        assertThat(Palindrome.testSentenceUTF8("too hot..45%$# to hoot."), is(true));
        assertThat(Palindrome.testSentenceUTF8("Hello World."), is(false));
        assertThat(Palindrome.testSentenceUTF8("śćaćś"), is(true));
        assertThat(Palindrome.testSentenceUTF8("śćaćśś"), is(false));
    }


}
