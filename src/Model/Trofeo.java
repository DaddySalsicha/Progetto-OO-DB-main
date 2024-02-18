package Model;

public class Trofeo {
    protected String nome;
    protected String anno;
    protected String merito;
    public Trofeo(String nome, String anno, String merito){
        this.nome = nome;
        this.anno = anno;
        this.merito = merito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getMerito() {
        return merito;
    }

    public void setMerito(String merito) {
        this.merito = merito;
    }
}
