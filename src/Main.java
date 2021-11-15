import utils.SimpleReader;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        //Lê o arquivo
        SimpleReader sr = new SimpleReader("./src/file/POP2021.csv");

        //Cria o map dos valores
        Map<String, String> cidades = new HashMap<>();
        //Lê a primeira linha do arquivo
        String linha = sr.readLine();

        //Cria os patterns para identificar o Estado e o Município
        Pattern patternEstado = Pattern.compile("^RS");
        Pattern patternMunicipio = Pattern.compile("^(Santa|Santo|São)");

        while (linha != null) {
            Matcher matcherEstado = patternEstado.matcher(linha);

            //Se a linha atual for referente ao estado do Tio Grande do Sul (RS)
            if (matcherEstado.find()) {
                //Quebra os valores da linha em lista
                String[] valores = linha.split(";");
                Matcher matcherMunicipio = patternMunicipio.matcher(valores[3]);

                //Se o Município começar com Santa, Santo ou São
                if (matcherMunicipio.find())
                    cidades.put(valores[3], valores[4]);

            }

            //Lê a próxima linha
            linha = sr.readLine();
        }

        //Fecha o arquivo
        sr.close();

        //Imprime os valores encontrados
        System.out.println("Municípios: " + cidades.size());
        cidades.keySet().forEach(key -> System.out.println(key + ": " + cidades.get(key)));
    }
}