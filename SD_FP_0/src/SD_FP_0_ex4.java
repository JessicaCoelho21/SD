import java.util.Scanner;

public class SD_FP_0_ex4 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Primeiro nome: ");
        String firstName = scanner.nextLine();

        System.out.println("Apelido: ");
        String lastName = scanner.nextLine();

        System.out.println(lastName + ", " + firstName);
    }
}
