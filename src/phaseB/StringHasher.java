package phaseB;
import providedCode.Hasher;


public class StringHasher implements Hasher<String> {
	
	@Override
	public int hash(String s) {
		int hashCode = 0;
		for(int i=0; i<s.length(); i++){
			hashCode += (s.charAt(i)*Math.pow(37.0, i));
		}
		return hashCode;
	}
}
