package negocio;

public class Professor extends Pessoa {
    
    private double salario;
    
    public Professor() {
        super();
        this.salario = 0.0;
    }

    public Professor(String nome, String cpf, int idade, double salario) {
        super(nome, cpf, idade);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    @Override
    public String toString() {
      //Chama o toString da classe Pai (Pessoa) e soma com as informações específicas para Professor
        String out = "";
        out += super.toString();
        out += "Salário: R$ " + salario + "\n";
        return out;
    }
}


