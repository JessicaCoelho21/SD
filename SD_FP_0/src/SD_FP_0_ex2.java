public class SD_FP_0_ex2 {
    public static void main(String[] args){
        int[] lista = {12, 5, -21, 10, -345, 22, 50, -125, 80, -1};
        int multiplicacao = 0, negativo = 0, maior = lista[0];

        //Resultado da multiplicação de todos os elementos positivos
        for(int i = 0; i <= lista.length - 1; i++) {
            if(lista[i] > 0) {
                if(multiplicacao == 0){
                    multiplicacao = lista[i];
                } else {
                    multiplicacao = multiplicacao * lista[i];
                }
            }
        }

        System.out.println("Multiplicação dos números positivos: " + multiplicacao);

        //Indentificar quantos elementos são negativos
        for (int i = 0; i <= lista.length - 1; i++) {
            if (lista[i] < 0){
                negativo++;
            }
        }

        System.out.println("Número de elementos negativos: " + negativo);

        //Identificar o maior número
        for (int i = 1; i <= lista.length - 1; i++) {
            if(lista[i] > maior) {
                maior = lista[i];
            }
        }

        System.out.println("Maior valor da lista: " + maior);
    }
}
