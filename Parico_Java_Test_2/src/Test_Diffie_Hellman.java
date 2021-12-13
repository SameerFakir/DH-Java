import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

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
	    assertEquals(false, Diffie_hellman.isPrime(z));

	}
	
	
	@Test
	@DisplayName("Output of public key should be mathematically correct")
	public void testGeneratePublicKey() 
	{
	    int mod = 23;
	    int base = 5;
	    int priKey = 4;
	    
	    BigInteger result = new BigInteger("4");
	    
	    assertEquals(result, Diffie_hellman.generatePublicKey(mod,base,priKey));
	}
	
	@Test
	@DisplayName("Output of secret key should be mathematically correct")
	public void testGenerateSecretKey() 
	{
	    int mod = 23;
	    int priKey = 4;
	    BigInteger pubKey = new BigInteger("10");
	    
	    BigInteger result = new BigInteger("18");
	    
	    assertEquals(result, Diffie_hellman.generateSecretKey(mod,priKey,pubKey));
	}
	

}
