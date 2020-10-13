public class SD_FP_0_ex1 {
    public static void main(String[] args){
        int [][] matrix = {{11, 7, 333},
                            {-20, -23, 63},
                            {-22, 501, 10000}};
        int soma = 0, count = 0;
        float media;

        //System.out.println(matrix.length);

        for (int i = 0; i <= matrix.length - 1; i++){
            for (int j = 0; j <= matrix.length - 1; j++){
                soma = soma + matrix[i][j];
                count++;
            }
        }

        System.out.println("Soma dos valores da matriz: " + soma);

        media = soma / count;
        System.out.println("Média dos valores da matriz: " + media);
    }
}
