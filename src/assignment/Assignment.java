/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/**
 *
 * @author bitzero
 */
public class Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BrokenOrRiskyCryptographicAlgorithm borca = new BrokenOrRiskyCryptographicAlgorithm();
        HashWithoutSalt hws = new HashWithoutSalt();
        ImproperExcessiveAuthenticationAttemptsRestriction ieaar = new ImproperExcessiveAuthenticationAttemptsRestriction();

        borca.test();
        hws.test();
        ieaar.test();

    }

}
/*

READ THIS:
I have created 3 classes. All are run from Assignment.java's main method. Comment the calls and run each at a time.

Each of the 3 classes demonstrate how the  Porous Defenses:
Each class has a porous and fixed method:
    -The porous() method calls to the method that demonstrates the porous defence.
    -The fixed() method calls to the method that demonstrates the fix to the porous defence.
   These total to 6 code blocks(3*2)

I have demonstrated:
    a) Broken or Risky Cryptographic Algorithm in class BrokenOrRiskyCryptographicAlgorithm.java
    b) Improper Restriction of Excessive Authentication Attempts in class ImproperExcessiveAuthenticationAttemptsRestriction.java
    c) Use of a One-Way Hash without a Salt in class HashWithoutSalt.java

Analyse this code carefully:
I have used DES encryption to demonstrate Broken or Risky Cryptographic Algorithm and SHA-256 encryprion for the secure fix of the DES encryption.

*/
