package icop.parcialpromociongonzalezig.dto;

public class ResponseSocioDto {

    private String nombreCompleto;
    private String dni;

    public ResponseSocioDto() {
    }

    public ResponseSocioDto(String nombreCompleto, String dni) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}