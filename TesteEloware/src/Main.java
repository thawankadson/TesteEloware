
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa();

        Funcionario f1 = new Funcionario("Maria", LocalDate.of(2000, 10,18),new BigDecimal("2009.44"), "Operador");
        Funcionario f2 = new Funcionario("João", LocalDate.of(1990, 5,12),new BigDecimal("2284.38"), "Operador");
        Funcionario f3 = new Funcionario("Caio", LocalDate.of(1961, 5,2),new BigDecimal("9836.14"), "Coordenador");
        Funcionario f4 = new Funcionario("Miguel", LocalDate.of(1988, 10,14),new BigDecimal("19119.88"), "Diretor");
        Funcionario f5 = new Funcionario("Alice", LocalDate.of(1995, 1,5),new BigDecimal("2234.68"), "Recepcionista");
        Funcionario f6 = new Funcionario("Heitor", LocalDate.of(1999, 11,19),new BigDecimal("1582.72"), "Operador");
        Funcionario f7 = new Funcionario("Arthur", LocalDate.of(1993, 3,31),new BigDecimal("4071.84"), "Contador");
        Funcionario f8 = new Funcionario("Laura", LocalDate.of(1994, 7,8),new BigDecimal("3017.45"), "Gerente");
        Funcionario f9 = new Funcionario("Heloisa", LocalDate.of(2003, 5,24),new BigDecimal("1606.85"), "Eletricista");
        Funcionario f10 = new Funcionario("Helena", LocalDate.of(1996, 9,2),new BigDecimal("2799.93"), "Gerente");

        empresa.inserirFuncionarios(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10));
        empresa.removerFuncionario("João");
        empresa.imprimirFuncionarios();

        System.out.println();
        System.out.println("Salario depois do aumento de 10%");
        empresa.aplicarAumentoSalario();
        empresa.imprimirFuncionarios();

        System.out.println();
        System.out.println("Imprimir agrupado por função:");
        empresa.imprimirAgrupadoPorFuncao();

        System.out.println();
        System.out.println("Funcionarios com MES 12 e MES 10");
        empresa.imprimirFuncionarioPorMesDesejado(10,12);

        System.out.println();
        System.out.println("Maior IDADE:");
        empresa.ordenarPorIdade();

        System.out.println();
        System.out.println("Ordenar por ORDEM ALFABETICA");
        empresa.imprimirPessoasOrdemAlfabetica();

        System.out.println();
        System.out.println("Salario TOTAL: ");
        empresa.somarSalarios();

        System.out.println();
        System.out.println("Quantos salarios minimos ganha cada funcionario?");
        empresa.contarExibirFuncionariosSalarioMinimo();


    }

}
