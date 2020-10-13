import java.util.regex.Pattern;

import static java.lang.Character.toLowerCase;

public class SD_FP_0_ex3 {
    public static void main(String[] args){
        char[] nome = {'A', 'n', 'a', ' ', 'S', 'a', 'n', 't', 'o', 's','\n'};
        String nomeS = new String(nome);
        int vogais = 0, consoantes = 0;

        //Nome e apelido no formato Apelido, Nome
        Pattern pattern = Pattern.compile("\\s");

        String[] temp = pattern.split(nomeS);
        String result = "";

        for (int i = 0; i < temp.length; i++) {
            if (i == temp.length - 1)
                result = temp[i] + result;
            else
                result = ", " + temp[i] + result;
        }

        System.out.println(result);

        //Número de vogais e consoantes
        for (int i = 0; i <= nome.length - 1; i++) {
            if (nome[i] == 'a' || nome[i] == 'e' || nome[i] == 'i' || nome[i] == 'o' || nome[i] == 'u'
                    || nome[i] == 'A' || nome[i] == 'E' || nome[i] == 'I' || nome[i] == 'O' ||
                    nome[i] == 'U') {
                vogais++;
            } else if (nome[i] == ' ' || nome[i] == '\n'){
                vogais = vogais;
                consoantes = consoantes;
            } else {
                consoantes++;
            }
        }

        System.out.println("Número de vogais: " + vogais);
        System.out.println("Número de consoantes: " + consoantes);
    }
}
