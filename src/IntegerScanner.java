import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerScanner implements Iterator<Integer> {

	private Reader r;
	private int nextInteger = 0;
	private int nextChar;
	private int index = 0;
	
    public IntegerScanner(java.io.Reader in) throws IOException {
    	if (in == null) {
    		throw new IllegalArgumentException();
    	} 
    	BufferedReader br = new BufferedReader(in);
    	r = br;
    	nextChar = r.read();
    	next();
    }
	
    public static IntegerScanner make(String filename) throws IOException {
        Reader r = new FileReader(filename);
        IntegerScanner d = new IntegerScanner(r);
        r.close();

        return d;
     }
    
    public int getIndex() {
    	return index;
    }
    
    public static boolean isValidInteger(char c) {
    	return (c >= 48 && c <= 57);
    }
    
	@Override
	public boolean hasNext() {
		if (nextChar == -1) {
			return false;
		} else {
			return true;
		}
	}

	//Returns 0 if not a valid integer
	@Override
	public Integer next() {
		if (!hasNext()) {
        	throw new NoSuchElementException();
        }
		int num = nextInteger;
		String str = "";
		char c;
		while (nextChar != -1 && nextChar != 10) {
			try {
				c = (char) nextChar;
				str += c;
				nextChar = r.read();
			} catch (IOException e) {}
		}
		try {
			int x = Integer.parseInt(str);
			nextInteger = x;
		} catch (NumberFormatException e) {
			nextInteger = 0;
		}
		if (nextChar == 10) {
			try {
				nextChar = r.read();
			} catch (IOException e) {}
		}
		index++;
		return num;
	}
}