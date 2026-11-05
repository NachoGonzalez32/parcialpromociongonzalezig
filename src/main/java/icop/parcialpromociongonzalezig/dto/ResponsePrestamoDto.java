package icop.parcialpromociongonzalezig.dto;

import java.time.LocalDate;

public class ResponsePrestamoDto {

    private Long id;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private boolean devuelto;
    private String nombreSocio;

    public ResponsePrestamoDto() {
    }

    public ResponsePrestamoDto(Long id, String tituloLibro,
                              LocalDate fechaPrestamo, boolean devuelto,
                              String nombreSocio) {
        this.id = id;
        this.tituloLibro = tituloLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.devuelto = devuelto;
        this.nombreSocio = nombreSocio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }
}