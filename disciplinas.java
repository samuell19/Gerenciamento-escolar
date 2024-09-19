public class disciplinas {
    private String nome;
    private String codigo;

    //essa classe é para armazenar os dados das disciplinas, sendo a mais simples do codigo
    


    //gets e sets
    public disciplinas(String nome, String codigo){
        this.nome = nome;
        this.codigo = codigo;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    @Override
    public String toString() {
        return "disciplinas: "+ this.nome + ", codigo: " + this.codigo;
    }
    
    
}
