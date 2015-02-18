package phaseA;
import providedCode.*;
//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2
/*
* Constructs an comparator of strings. The strings are compared alphabetically,
* and if one string includes the other one, the shorter one is smaller the the
* longer one.
*/
public class StringComparator implements Comparator<String> {

	// Given two Strings, returns a negative number when first string to
	// compare comes first alphabetically.
	@Override
	public int compare(String s1, String s2) {
		for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
			if (s1.charAt(i) < s2.charAt(i)) {
				return -1;
			} else if (s1.charAt(i) > s2.charAt(i)) {
				return 1;
			}
		}
		// If one string's letters are exactly same as part of the other
		// Returns difference between two string's lengths
		return s1.length() - s2.length();
	}  
}