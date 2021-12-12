import java.util.InputMismatchException;
import java.util.Scanner;

public class Diffie_hellman {
	
	public static void main(String[] args){
		
		int p = getModulus();
		int g = getBase();
		
		// Alice's private key
		System.out.println("Enter Alice's Private Key: ");
		int alicePrivateKey = getPriKeyInput();
		
		// Bob's private key
		System.out.println("Enter Bob's Private Key: ");
		int bobPrivateKey = getPriKeyInput();
		
		generate_keys(p,g,alicePrivateKey,bobPrivateKey);
	}
	
	public static int getModulus(){
		System.out.println("Enter Modulus value: ");
		int p = getPrimeInput();
		return p;
	}
	
	
	public static int getBase(){
		System.out.println("Enter Base value: ");
		int g = getPrimeInput();
		return g;
	}
	
	
	
	public static int getPrimeInput(){
		//Boolean check to loop until valid input
		boolean valid = false;
		
		//Do loop for exception handling on incorrect input
		do {
			try {
				//Scanner used to take user input
				Scanner sc = new Scanner(System.in);
				int g = sc.nextInt();
				//If taken as int, check passed and input returned, loop ends
				//Check if input is prime
				if(isPrime(g)==true){
					valid = true;
					return g;
				}
				else{
					System.out.println("Input is not a prime number");
					//Input loop continues as number is not prime
				}
			} catch (InputMismatchException e) {
				//If unable to read as int, check not passed, loop continues
				System.out.println("Invalid Input, enter an integer");
			}
			return getPrimeInput();
		} while (valid);
		
		
	}
	
	public static int getPriKeyInput(){
		//Boolean check to loop until valid input
		boolean valid = false;
		
		//Do loop for exception handling on incorrect input
		do {
			try {
				//Scanner used to take user input
				Scanner sc = new Scanner(System.in);
				int g = sc.nextInt();
				//If taken as int, check passed and input returned, loop ends
				valid = true;
				return g;
			} catch (InputMismatchException e) {
				//If unable to read as int, check not passed, loop continues
				System.out.println("Invalid Input, enter an integer");
			}
			return getPriKeyInput();
		} while (valid);
		
		
	}
	
	/*
	 * @param 	modulus - 
	 * @param 	base - 
	 * @param 	alicePrivateKey - 
	 * @param 	bobPrivateKey - 
	 * @return	null 
	 */
	
	public static void generate_keys(int modulus, int base, int alicePrivateKey, int bobPrivateKey) {
		
		//a = Alice 4
		//b = Bob 3
		//result for above should be 18
		
		//p = 157
		//b = 967
	
		
		// Alice's encrypted message to Bob
		long alicePublicKey = generatePublicKey(modulus,base,alicePrivateKey);
		System.out.println("Alice's public key: " + String.valueOf(alicePublicKey));
		
		// Bob's encrypted message to Alice
		long bobPublicbKey = generatePublicKey(modulus,base,bobPrivateKey);
		System.out.println("Bob's public key: " + String.valueOf(bobPublicbKey));
		
		// Alice generates shared secret key
		long aSecKey = generateSecretKey(modulus,alicePrivateKey,bobPublicbKey);
		System.out.println("Alice's shared secret key is: " + String.valueOf(aSecKey));
		
		// Bob generates shared secret key
		long bSecKey = generateSecretKey(modulus,bobPrivateKey,alicePublicKey);
		System.out.println("Bob's shared secret key is: " + String.valueOf(bSecKey));
		
		// TEST System.out.println("Test : " + String.valueOf( (long) Math.pow(967, 4) % 157));
		
		return;
	}
	
	/*
	 * @param 	n - input integer to test if prime 
	 * @return	boolean that indicates if input is prime or not 
	 */
	
	public static boolean isPrime(int n) {
		// n -
        if((n > 2 && n % 2 == 0) || n == 1) { //removes 1 and even numbers over 2
            return false;
        }

        for (int i = 3; i <= (int)Math.sqrt(n); i += 2) { 
        		//only checks odds numbers
        		//goes up to square root of n for efficiency 

            if (n % i == 0) {
            		//checks if odd number can divide into input
            	
                return false;
            }
        }
        
        return true;
    }

	/*
	 * @param 	modulus - 
	 * @param 	base - 
	 * @param 	alicePrivateKey - 
	 * @param 	bobPrivateKey - 
	 * @return	null 
	 */
	
	public static long generatePublicKey(int mod, int base, long priKey){
		
		//Method to generate public key given modulus, base and private key
		long pubKey = (long) Math.pow(base, priKey) % mod;
		
		/* System.out.println(
				String.valueOf(newKey) + " = " 
				+ String.valueOf(base)
				+ "^" + String.valueOf(priKey)
				+ " mod " + String.valueOf(mod)
		);
		// int pubKey = Math.toIntExact(newKey);
		 */
		return pubKey;
	}
	
	public static long generateSecretKey(int mod, long priKey, long pubKey){
		
		//Method to generate secret key given modulus, public key and own private key
		long secKey = (long) Math.pow(pubKey, priKey) % mod;
		/*
		System.out.println(
				String.valueOf(newKey) + " = " 
				+ String.valueOf(pubKey)
				+ "^" + String.valueOf(priKey)
				+ " mod " + String.valueOf(mod)
		);
		int secKey = Math.toIntExact(newKey);
		*/
		return secKey;
	}

	
	
	
	
	
	/*
	 * TO DO 
	 * 
	 * Clean up code
	 * Finish Unit testing
	 * Test in older java
	 * 
	 */
	
}
