import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Empresa {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Funcionario> listFuncionarios = new ArrayList<>();

    public void inserirFuncionarios(List<Funcionario> funcionario){
        listFuncionarios.addAll(funcionario);
    }

    public void removerFuncionario(String nome){
        listFuncionarios.removeIf(funcionario -> funcionario.nome.equals(nome));
    }

    public void imprimirFuncionarios(){
        listFuncionarios.forEach( funcionario -> {
            System.out.println("Nome: " + funcionario.getNome() + ", Data de Nascimento: " + funcionario.getDataNascimento().format(formatter) + ", Salario: "+ funcionario.getSalario() + ", Função: " + funcionario.getFuncao());
        });
    }

    public void aplicarAumentoSalario() {
        for (Funcionario funcionario : listFuncionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal percentualAumento = new BigDecimal("0.10");
            BigDecimal aumento = salarioAtual.multiply(percentualAumento);
            BigDecimal novoSalario = salarioAtual.add(aumento);

            funcionario.setSalario(novoSalario);
        }
    }

    public static BigDecimal aplicarAumento(BigDecimal salario) {
        BigDecimal percentualAumento = new BigDecimal("0.10");
        BigDecimal aumento = salario.multiply(percentualAumento);
        return salario.add(aumento);
    }

    public void imprimirFuncionarioPorMesDesejado(int mes, int mes2){
        for (Funcionario x: listFuncionarios){
            if(x.getDataNascimento().getMonthValue()==mes || x.getDataNascimento().getMonthValue()==mes2){
                System.out.println(x.getNome() + " " + x.getDataNascimento().format(formatter));
            }
        }
    }

    public void somarSalarios() {
        BigDecimal total = BigDecimal.ZERO;

        for (Funcionario salario : listFuncionarios) {
            total = total.add(salario.getSalario());
        }
        System.out.println("Total salario: " + total);
    }

    public void imprimirPessoasOrdemAlfabetica(){
        Collections.sort(listFuncionarios);
        listFuncionarios.forEach( funcionario ->
                System.out.println("Nome: "+funcionario.getNome()
                        + ", Data de Nascimento: " + funcionario.getDataNascimento().format(formatter)
                        + ", Salario: " + funcionario.getSalario()
                        + ", Funcao: " + funcionario.getFuncao()));
    }

    public void ordenarPorIdade() {
        Collections.sort(listFuncionarios, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p1, Pessoa p2) {
                return p1.getDataNascimento().compareTo(p2.getDataNascimento());
            }

        });
        System.out.println(listFuncionarios.get(0).getNome() + " " + listFuncionarios.get(0).getDataNascimento().format(formatter));
    }

    public void imprimirAgrupadoPorFuncao(){
        Map<String, List<Funcionario>> funcionariosPorFuncao = listFuncionarios.stream()
                .collect(Collectors.groupingBy(funcionario -> funcionario.funcao));

        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função " + funcao);
            lista.forEach(funcionario -> System.out.println("   " + funcionario.nome));
        });
    }

    public void contarExibirFuncionariosSalarioMinimo() {
        for (Funcionario funcionario : listFuncionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(BigDecimal.valueOf(1212.00), 2, RoundingMode.HALF_UP);
            int numeroSalarios = salariosMinimos.intValue(); // Convertendo para um número inteiro

            System.out.println("Nome: " + funcionario.getNome() + ", ganha " + numeroSalarios + " salários mínimos.");
        }
    }

}
