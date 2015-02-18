package test;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestSorter {
	private static final int TIMEOUT = 2000; 
	protected DataCounter<E> dc;
	protected abstract DataCounter<E> getDataCounter();
	
	@Before
	public void setUp() {
		dc = getDataCounter();
	}

	

    public void test()
    {
    }

}
