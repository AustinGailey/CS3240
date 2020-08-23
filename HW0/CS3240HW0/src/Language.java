import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/**
 * Represents a finite language.
 *
 * @author Dr. Jody Paul
 * @author Austin Gailey
 * @version 1.3.1
 */
public final class Language extends Object implements Iterable<String>, java.io.Serializable {
    /** The empty string. */
    private static final String EMPTY_STRING = "";
    /** The empty set. */
    private static final Set<String> EMPTY_SET = new TreeSet<String>();

    /** The set of strings in this language, initially empty. */
    private Set<String> strings;

    /**
     * Create a language with no strings.
     */
    public Language() {
        strings = new TreeSet<>(EMPTY_SET);
    }

    /**
     * Indicates if this language has no strings.
     * @return true if the language is equivalent to the empty set;
     *         false otherwise
     */
    public boolean isEmpty() {
        return strings.isEmpty();
    }

    /**
     * Accesses the number of strings (cardinality) in this language.
     * @return the cardinality of the language
     */
    public int cardinality() {
        return strings.size();
    }

    /**
     * Determines if a specified string is in this language.
     * @param candidate the string to check
     * @return true if the string is in the language,
     *         false if not in the language or the parameter is null
     */
    public boolean includes(final String candidate) {
        return candidate != null && strings.contains(candidate);
    }

    /**
     * Ensures that this language includes the given string
     * (adds it if necessary).
     * @param memberString the string to be included in the language
     * @return true if this language changed as a result of the call
     */
    public boolean addString(final String memberString) {
        if(!strings.contains(memberString)){
            strings.add(memberString);
            return true;
        }
        return false;
    }

    /**
     * Ensures that this language includes all of the strings
     * in the given parameter (adds any as necessary).
     * @param memberStrings the strings to be included in the language
     * @return true if this language changed as a result of the call
     */
    public boolean addAllStrings(final Collection<String> memberStrings) {
        return strings.addAll(memberStrings);
    }

    /**
     * Provides an iterator over the strings in this language.
     * @return an iterator over the strings in this language in ascending order
     */
    public Iterator<String> iterator() {
        return strings.iterator();
    }

    /**
     * Creates a language that is the concatenation of this language
     * with another language.
     * @param language the language to be concatenated to this language
     * @return the concatenation of this language with the parameter language
     */
    public Language concatenate(final Language language) {
        if(this.isEmpty()||language.isEmpty()) return new Language();
        Language concatenatedLanguage = new Language();
        for(String s1 : strings){
            for(String s2 : language.strings){
                concatenatedLanguage.addString(s1+s2);
            }
        }
        return concatenatedLanguage;
    }

    /**
    * Tests whether two language sets are equal.
    * @param obj the language to test equality
    * @return true if the language sets are equal
    * */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Language) {
            return strings.hashCode() == ((Language) obj).strings.hashCode();
        }
        return false;
    }

    /**
    * Returns the hash code of a language set.
    * @return int as hash code
    * */
    @Override
    public int hashCode() {
        return strings.hashCode();
    }
}