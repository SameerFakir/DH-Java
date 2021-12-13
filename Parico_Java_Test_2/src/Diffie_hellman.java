import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Diffie_hellman {
	
	public static void main(String[] args){
		
		// Obtain modulus input
		int p = getModulus();
		
		// Obtain base input
		int g = getBase();
		
		// Obtain Alice's private key
		System.out.print("Enter Alice's Private Key: ");
		int alicePriKey = getPriKeyInput();
		
		// Obtain Bob's private key
		System.out.print("Enter Bob's Private Key: ");
		int bobPriKey = getPriKeyInput();
		
		// Method used to generate and print all new keys
		generate_keys(p,g,alicePriKey,bobPriKey);
	}
	
	/*
	 * @return	p	Prompts user to enter prime number for modulus value, returns modulus value entry				
	 */
	
	public static int getModulus(){
		System.out.print("Enter Modulus value: ");
		int mod = getPrimeInput();
		return mod;
	}
	
	/*
	 * @return	g	Prompts user to enter prime number for base value, returns base value entry				
	 */
	
	public static int getBase(){
		System.out.print("Enter Base value: ");
		int base = getPrimeInput();
		return base;
	}
	
	/*
	 * @return	chosenPrime	Processes user input to verify if acceptable as integer for private keys				
	 */
	
	public static int getPrimeInput(){
		// Boolean check to loop until valid input
		boolean valid = false;
		
		// Do loop for exception handling on incorrect input
		do {
			
			try {
				// Scanner used to take user input
				Scanner sc = new Scanner(System.in);
				int chosenPrime = sc.nextInt();
				
				// Check if input is prime
				if(isPrime(chosenPrime)==true){
					valid = true;
					return chosenPrime;
				}
				else{
					System.out.println("Input must be a prime number greater than 1");
					// Input loop continues as number is not prime
				}
				
			} catch (InputMismatchException e) {
				// If unable to read as int, check not passed, loop continues
				System.out.println("Invalid Input, enter a positive prime number");
			}
			return getPrimeInput();
		} while (valid);
		
		
	}
	
	/*
	 * @return	key		Processes user input to verify if acceptable as integer for private keys				
	 */
	
	public static int getPriKeyInput(){
		// Boolean check to loop until valid input
		boolean valid = false;
		
		// Do loop for exception handling on incorrect input
		do {
			try {
				// Scanner used to take user input
				Scanner sc = new Scanner(System.in);
				int key = sc.nextInt();
				// If taken as int, check passed and input returned, loop ends
				if(key < 1) {
					valid = false;
					System.out.println("Private key must be positive integer");
				}
				else {
					valid = true;
					return key;
				}
			} catch (InputMismatchException e) {
				// If unable to read as int, check not passed, loop continues
				System.out.println("Invalid Input, enter a positive integer");
			}
			return getPriKeyInput();
		} while (valid);
		
		
	}
	
	/*
	 * @param 	mod  			Modulus input that the user has selected 
	 * @param 	base  			Base input that the user has selected
	 * @param 	alicePriKey 	Alice's inputed private key
	 * @param 	bobPriKey 		Bob's inputed private key
	 * @return	null				
	 */
	
	
	public static void generate_keys(int mod, int base, int alicePriKey, int bobPriKey) {
		
		// Alice's encrypted message to Bob
		BigInteger alicePubKey = generatePublicKey(mod,base,alicePriKey);
		
		// Bob's encrypted message to Alice
		BigInteger bobPubKey = generatePublicKey(mod,base,bobPriKey);
		
		
		// Alice generates shared secret key
		BigInteger aliceSecKey = generateSecretKey(mod,alicePriKey,bobPubKey);
		
		// Bob generates shared secret key
		BigInteger bobSecKey = generateSecretKey(mod,bobPriKey,alicePubKey);
		
		System.out.println("\nBase used:    " + String.format("%7d",base));
		System.out.println("Modulus used: " + String.format("%7d",mod));
		
		
		System.out.println(
						"\nName  | Private Key | Public Key | Shared Key\n"
						+ "------+-------------+------------+-----------" 
						+ "\nAlice | " + String.format("%11d",alicePriKey) + " | " + String.format("%10d",alicePubKey) + " | " + String.format("%10d",aliceSecKey) 
						+ "\nBob   | " + String.format("%11d",bobPriKey) + " | " + String.format("%10d",bobPubKey) + " | " + String.format("%10d",bobSecKey));
		
		return;
	}
	
	/*
	 * @param 	n	Input integer to test if prime 
	 * @return		Boolean that indicates if input is prime or not 
	 */
	
	public static boolean isPrime(int n) {
	
        if((n > 2 && n % 2 == 0) || n < 1 ) { 
        	// Checks if number is divisible by 2 or if number is less than 1 in which case they are not prime
            return false;
        }

        for (int i = 3; i <= (int)Math.sqrt(n); i += 2) { 
        		// Only checks odds numbers (as all even over 2 are not prime)
        		// Goes up to square root of n for efficiency 

            if (n % i == 0) {
            	// Checks if n can divide into odd number i 
            	// If so then n is not a prime
            	
                return false;
            }
        }
        
        return true;
    }

	/*
	 * @param 	mod  	Modulus input that the user has selected 
	 * @param 	base  	Base input that the user has selected
	 * @param 	priKey 	User's selected private key
	 * @return	pubKey	Returns newly calculated public key using previous parameters	
	 */
	
	public static BigInteger generatePublicKey(int mod, int base, int priKey){
		
		// BigInteger used for calculation as output may go outside constraints of integer value type
		BigInteger pubKey = (
				BigInteger.valueOf(base)
				.pow(priKey))
				.mod(BigInteger.valueOf(mod));
		
		return pubKey;
	}
	
	/*
	 * @param 	mod  	Modulus input that the user has selected 
	 * @param 	priKey  Base input that the user has selected as their private key
	 * @param 	pubKey 	Previously generated public key is used to calculate secret key
	 * @return	secKey	Returns newly calculated shared secret key using previous parameters	
	 */
	
	public static BigInteger generateSecretKey(int mod, int priKey, BigInteger pubKey){
		
		// BigInteger used for calculation as output may go outside constraints of integer value type
		BigInteger secKey =  (pubKey.pow(priKey))
							.mod(BigInteger.valueOf(mod));
		
		return secKey;
	}

	
	
	
	
	
	
}
