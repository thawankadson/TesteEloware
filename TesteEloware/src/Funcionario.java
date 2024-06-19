import java.math.BigDecimal;
import java.time.LocalDate;

class Funcionario extends Pessoa{
    BigDecimal salario;
    String funcao;

    //Getters e setters são usados para proteger seus dados, especialmente na criação de classes.
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario=salario;
        this.funcao = funcao;
    }

    Funcionario(String nome, LocalDate dataNascimento) {
        super(nome, dataNascimento);
    }

    @Override
    public int compareTo(Pessoa p) {
        return this.getNome().compareTo(p.getNome());
    }

    @Override
    public String toString() {
        return "Funcionario: " + nome + ", dataNascimento: " + dataNascimento;
    }
}