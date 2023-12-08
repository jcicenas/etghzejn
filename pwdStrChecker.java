import javax.sound.sampled.SourceDataLine;
import java.util.*;
public class pwdStrChecker{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Enter your password: ");
            String temp = scan.nextLine();
            updateScreen(temp);
        } while (InputHelper.getYNConfirm(scan, "Enter y/n to stop"));
    }

    private static void updateScreen(String password){
        //initializing variables
        int characters = 0;
        long bits = 0;
        String strength = "";
        double time = 0;
        System.out.println(password);
        
        //hides the element warning again on new character inputed - otherwise warning would stay up f
        
        //uses regular expressions and the .match method to find characters in the user input
        //.match method searches through user input string to find regular expression
        //matching lowercase letters - if a lowercase letter is found in the string add 26 to integer 
        if(password.matches(".*[a-z].*")){
            characters = characters + 26;
        }
        //matching uppercase letters - if an uppercase letter is found in the string add 26 to integer
        if(password.matches(".*[A-Z].*")){
            characters = characters + 26;
        }
        //matching numbers - if a number is found in the string add 10 to integer characters
        if(password.matches(".*[0-9].*")){
            characters = characters + 10;
        }
        //matching symbols - if a symbol is found in the string add 21 to integer characters
        if(password.matches(".*[!()-.?_`~;:!@#$%^&*=+]+.*")){
            characters = characters + 21;
        }
        System.out.println(characters);
        //password strength is calculated in bits
        //for example if the password is six characters long and all lowercase it is 26 ^ 6
        //if it has uppercase 52^6
        //this expanded is the total sample space
        //to convert to bits we need to convert the sample space to base two (formula below)
        //formula for bits is 1 + log2(possible characters^ length of password)
        //using formula to calculate bits
        bits = Math.round(1 + Math.log(Math.pow(characters, password.length())) / Math.log(2));
        System.out.println(bits);
        //using 150 billion hp/s (Attainable with market grade gear) to calculate time
        time = Math.pow(2, bits) / (150000000000l * 6.308 * Math.pow(10, 7));
        //if less than a second time to crack is instant
        if(time <= 0.000000031709792){
            System.out.println("Estimated Time to Crack Password: " + "\nInstant");
        
        }
        //if less than a minute time is put in seconds
        else if(time <= 0.00000190258){
            System.out.println("Estimated Time to Crack Password: \n" + Math.round(time* 31536000) + " seconds");
        
        }
        //if less than an hour time is calculate in minutes
        else if(time <= 0.00011415525){
            System.out.println("Estimated Time to Crack Password: \n"+ Math.round(time* 525600) + " minutes");
        
        }
        //if less than a day time is calculated in hours
        else if(time <= 0.0027){
            System.out.println("Estimated Time to Crack Password: \n"+Math.round(time* 8760) + " hours");
        
        }
        //if less than a year time is calculated in days
        else if(time <= 1){
            System.out.println("Estimated Time to Crack Password: \n"+ Math.round(time* 365) + " days");
        
        }
        //else time is calculated in years
        else{
            System.out.println("Estimated Time to Crack Password: \n" + Math.round(time) + " years");
        }
        //using number of bits to determine password strength
        if(bits <= 35){
        strength = "Weak";
        }else if(bits <= 59){
        strength = "Moderate";
        }else if(bits <= 127){
        strength = "Good";
        }else if(128 <= bits){
        strength = "Excellent";
        }
        System.out.println(strength);
    }
}