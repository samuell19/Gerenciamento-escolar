public class registro_disciplinas {
    
    
 //classe para registrar as disciplinas do aluno e suas notas, assim como sua média. ela tem ligação direta com a classe disciplinas.
    
    private disciplinas disciplinas;
    private double nota1;
    private double nota2;
    private double nota_final;
    private double nota_semestre;


    //construtor
    public registro_disciplinas(disciplinas disciplinas, double nota1, double nota2, double nota_final) {
        this.disciplinas=disciplinas;
        this.nota1=nota1;
        this.nota2=nota2;
        this.nota_semestre=notaSemestre();
        this.nota_final=-1;
    }
   //excluir depois
   public double notaSemestre() {
    return (nota1 + nota2) / 2;
}

//metodo para a aprovação. o motivo da nota final ser -1 é para que ela não seja considerada na média do aluno, porque quando ela for calculada vai ser diferente
//de -1
public String verificar_aprovacao() {
    double mediaSemestre = notaSemestre();

    if (mediaSemestre >= 60) {
        return "Passou por média. Média: " + mediaSemestre;
    } else if (nota_final != -1) {
       
        double mediaFinal = (mediaSemestre + nota_final) / 2;

        if (mediaFinal >= 60) {
            return "Passou na prova final. Média final: " + mediaFinal;
        } else {
            return "Reprovou no semestre. Média final: " + mediaFinal;
        }
    } else {
        return "Ficou de recuperação. Média: " + mediaSemestre + ". Necessita realizar prova final.";
    }
}


//gettters e setters
    public disciplinas getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(disciplinas disciplinas) {
        this.disciplinas = disciplinas;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota_final() {
        return nota_final;
    }

    public void setNota_final(double nota_final) {
        this.nota_final = nota_final;
    }

    public double getNota_semestre() {
        return nota_semestre;
    }

    public void setNota_semestre(double nota_semestre) {
        this.nota_semestre = nota_semestre;
    }
    
    
}
