import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class SD_FP_0_ex5 {
    public static void main (String[] args) {
        int[] listaA = {2, -5, -121, 102, -35, -2, 0, -125, 802, -10};
        int[] listaB = {6, 99, -1, 12, 1, -2};
        int aLength = listaA.length;
        int bLength = listaB.length;
        int[] unionAB = new int[aLength + bLength];

        //Unir vetores
        System.arraycopy(listaA, 0, unionAB, 0, aLength);
        System.arraycopy(listaB, 0, unionAB, aLength, bLength);
        System.out.println("Arrays unidos: " + Arrays.toString(unionAB));

        //Valores repetidos nos arrays
        int repetidos = 0;

        for (int i = 0; i <= unionAB.length - 1; i++) {
            for (int j = i + 1; j <= unionAB.length - 1; j++) {
                if (unionAB[i] == unionAB[j]) {
                    repetidos++;
                }
            }
        }

        System.out.println("Valores repetidos: " + repetidos);

        //Preencher um novo vetor com os elementos da listaA que não se encontram na listaB
        Set<Integer> hset2 = new LinkedHashSet<>();
        for (int i = 0; i < listaA.length; i++) {
            for (int j = 0; j < listaB.length; j++) {
                if (listaA[i] != listaB[j]) {
                    hset2.add(listaA[i]);
                }
            }
        }

        System.out.println("Array com os elementos de A que não se encontram em B: " + hset2);

        /*
         * Preencher um novo vetor com os elementos que se encontram
         * simultaneamente nos dois vetores (sem repetidos).
         */
        Set<Integer> hset = new LinkedHashSet<>();
        for (int i = 0; i < listaA.length; i++) {
            for (int j = 0; j < listaB.length; j++) {
                if (listaA[i] == listaB[j]) {
                    hset.add(listaA[i]);
                }
            }
        }

        System.out.println("Array com elementos em simultâneo: " + hset);
    }
}
