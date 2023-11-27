import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10,18),new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5,12),new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5,2),new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10,14),new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1,5),new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11,19),new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3,31),new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7,8),new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5,24),new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9,2),new BigDecimal("2799.93"), "Gerente"));

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        imprimirFuncionarios(funcionarios);

        // 3.4 – Os funcionários receberam 10% de aumento de salário
        funcionarios.forEach(funcionario -> funcionario.aplicarAumento(new BigDecimal("0.10")));

        // 3.5 – Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        imprimirAniversariantes(funcionarios, 10);
        imprimirAniversariantes(funcionarios, 12);

        // 3.9 – Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = encontrarFuncionarioMaisVelho(funcionarios);
        imprimirInformacoesFuncionarioMaisVelho(funcionarioMaisVelho);

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        imprimirFuncionariosOrdenados(funcionarios);

        // 3.11 – Imprimir o total dos salários dos funcionários
        imprimirTotalSalarios(funcionarios);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        imprimirSalariosMinimos(funcionarios);
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(formatter),
                    funcionario.getSalario().toString(),
                    funcionario.getFuncao());
        }
        System.out.println();
    }

    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("Funcionários por Função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.printf("Função: %s%n", funcao);
            imprimirFuncionarios(lista);
        });
    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios, int mes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.printf("Aniversariantes do mês %d:%n", mes);
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .forEach(funcionario -> {
                    System.out.printf("Nome: %s, Data de Nascimento: %s%n",
                            funcionario.getNome(),
                            funcionario.getDataNascimento().format(formatter));
                });
        System.out.println();
    }

    private static Funcionario encontrarFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);
    }

    private static void imprimirInformacoesFuncionarioMaisVelho(Funcionario funcionario) {
        System.out.println("Informações do Funcionário Mais Velho:");
        System.out.printf("Nome: %s%n", funcionario.getNome());
        System.out.printf("Data de Nascimento: %s%n", funcionario.getDataNascimento());
        System.out.printf("Salário: %.2f ", funcionario.getSalario());
        System.out.printf(" Função: %s%n", funcionario.getFuncao());
        System.out.println();
    }


    private static void imprimirFuncionariosOrdenados(List<Funcionario> funcionarios) {
        System.out.println("Funcionários por Ordem Alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> System.out.printf("Nome: %s%n", funcionario.getNome()));
        System.out.println();
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        System.out.println("Total dos Salários dos Funcionários:");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total: %s%n", totalSalarios.toString());
        System.out.println();
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("Salários em Salários Mínimos:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioEmSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("%s: %.2f salários mínimos%n",
                    funcionario.getNome(),
                    salarioEmSalariosMinimos.doubleValue());
        });
    }
}

