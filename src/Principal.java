import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        System.out.println("FUNCIONÁRIOS");
        imprimirFuncionarios(funcionarios);

        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario()
                    .multiply(new BigDecimal("1.10"))
                    .setScale(2, RoundingMode.HALF_UP);

            funcionario.setSalario(novoSalario);
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = new LinkedHashMap<>();

        for (Funcionario funcionario : funcionarios) {
            funcionariosPorFuncao
                    .computeIfAbsent(funcionario.getFuncao(), funcao -> new ArrayList<>())
                    .add(funcionario);
        }

        System.out.println();
        System.out.println("FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");

        for (Map.Entry<String, List<Funcionario>> grupo : funcionariosPorFuncao.entrySet()) {
            System.out.println();
            System.out.println(grupo.getKey());
            imprimirFuncionarios(grupo.getValue());
        }

        System.out.println();
        System.out.println("ANIVERSARIANTES DOS MESES 10 E 12");

        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();

            if (mes == 10 || mes == 12) {
                System.out.println(funcionario.getNome());
            }
        }

        Funcionario maisVelho = funcionarios.getFirst();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = funcionario;
            }
        }

        int idade = Period.between(
                maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println();
        System.out.println("FUNCIONÁRIO COM MAIOR IDADE");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + idade);

        List<Funcionario> ordemAlfabetica = new ArrayList<>(funcionarios);

        ordemAlfabetica.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println();
        System.out.println("FUNCIONÁRIOS EM ORDEM ALFABÉTICA");

        imprimirFuncionarios(ordemAlfabetica);

        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        System.out.println();
        System.out.println("TOTAL DOS SALÁRIOS");
        System.out.println(formatarValor(totalSalarios));

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println();
        System.out.println("SALÁRIOS MÍNIMOS POR FUNCIONÁRIO");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantidade = funcionario.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            System.out.println(
                    funcionario.getNome()
                            + ": "
                            + formatarValor(quantidade)
                            + " salários mínimos");
        }
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(
                    "Nome: " + funcionario.getNome()
                            + " | Data de nascimento: "
                            + funcionario.getDataNascimento().format(formatoData)
                            + " | Salário: "
                            + formatarValor(funcionario.getSalario())
                            + " | Função: "
                            + funcionario.getFuncao());
        }
    }

    public static String formatarValor(BigDecimal valor) {

        DecimalFormatSymbols simbolos =
                new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR"));

        DecimalFormat formato =
                new DecimalFormat("#,##0.00", simbolos);

        return formato.format(valor);
    }
}