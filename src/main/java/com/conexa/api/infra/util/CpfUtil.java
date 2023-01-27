package com.conexa.api.infra.util;

public class CpfUtil {

    public static Boolean validarCpf(String cpf) {
        Boolean numerosIguais = true;
        char primeiro = cpf.charAt(0);
        for (int i = 0; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiro) {
                numerosIguais = false;
                break;
            }
        }

        if (numerosIguais)
            return false;

        int[] multiplicadores = new int[]{ 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

        int soma1 = 0;
        for (int i = 1; i < multiplicadores.length; i++) {
            soma1 += multiplicadores[i] * Character.getNumericValue(cpf.charAt(i - 1));
        }

        int resultadoDigito1 = 11 - (soma1 % 11);
        String digito1 = "";
        if (resultadoDigito1 > 9) {
            digito1 = "0";
        } else {
            digito1 = String.valueOf(resultadoDigito1);
        }

        String cpfCorreto = cpf.substring(0, 9).concat(digito1);

        int soma2 = 0;
        for (int i = 0; i < multiplicadores.length; i++) {
            soma2 += multiplicadores[i] * Character.getNumericValue(cpfCorreto.charAt(i));
        }

        int resultadoDigito2 = 11 - (soma2 % 11);
        String digito2 = "";
        if (resultadoDigito2 > 9) {
            digito2 = "0";
        } else {
            digito2 = String.valueOf(resultadoDigito2);
        }

        cpfCorreto = cpfCorreto.concat(digito2);

        return cpf.equals(cpfCorreto);
    }


}
