package POJO;

/**
 * Created by gabi on 02/09/2016.
 */
public class ObjHorario {
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getId_horario() {
        return Id_horario;
    }

    public void setId_horario(int id_horario) {
        Id_horario = id_horario;
    }

    public int getId_onibus() {
        return Id_onibus;
    }

    public void setId_onibus(int id_onibus) {
        Id_onibus = id_onibus;
    }

    String hora;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    int dia;
    int Id_horario;
    int Id_onibus;
}
