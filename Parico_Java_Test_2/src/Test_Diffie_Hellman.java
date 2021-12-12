import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Test_Diffie_Hellman {

	
	@Test
	@DisplayName("Primes should be validated correctly")
	public void testIsPrime() 
	{
	    int x  = 7 ;
	    assertEquals(true, Diffie_hellman.isPrime(x));
	    
	    int y = 7919;
	    assertEquals(true, Diffie_hellman.isPrime(y));
	    
	    int z = -17;
	    assertEquals(true, Diffie_hellman.isPrime(z));

	}
	
	
	@Test
	@DisplayName("")
	public void testGeneratePublicKey() 
	{
	    int mod = 23;
	    int base = 5;
	    int priKey = 4;
	    assertEquals(4, Diffie_hellman.generatePublicKey(mod,base,priKey));
	}
	
	@Test
	public void testGenerateSecretKey() 
	{
	    int mod = 23;
	    int priKey = 4;
	    int pubKey = 10;
	    
	    assertEquals(18, Diffie_hellman.generateSecretKey(mod,priKey,pubKey));
	}
	

}
