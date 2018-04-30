package POJO;

/**
 * Created by gabi on 01/10/2016.
 */
public class ObjMensagem {
    String nome;

    public String getData() {
        return data;
    }

    public String getTextoPronto() {
        return textoPronto;
    }

    public void setTextoPronto(String textoPronto) {
        this.textoPronto = textoPronto;
    }

    public String 	textoPronto;

    public void setData(String data) {
        this.data = data;
    }

    String data;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    String mensagem;
}
