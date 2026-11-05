package icop.parcialpromociongonzalezig.service;

import java.util.List;

import org.springframework.stereotype.Service;

import icop.parcialpromociongonzalezig.dto.RequestSocioDto;
import icop.parcialpromociongonzalezig.dto.ResponseSocioDto;
import icop.parcialpromociongonzalezig.entity.Socio;
import icop.parcialpromociongonzalezig.exception.RecursoNoEncontradoException;
import icop.parcialpromociongonzalezig.exception.ReglaNegocioException;
import icop.parcialpromociongonzalezig.repository.SocioRepository;

@Service
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    public List<ResponseSocioDto> listarSocios() {
        return socioRepository.findAll().stream()
                .map(this::convertirADto)
                .toList();
    }

    public ResponseSocioDto buscarPorId(Long id) {
        Socio socio = obtenerSocio(id);
        return convertirADto(socio);
    }

    public ResponseSocioDto registrarSocio(RequestSocioDto dto) {
        if (socioRepository.existsByDni(dto.getDni())) {
            throw new ReglaNegocioException(
                    "Ya existe un socio con el DNI: " + dto.getDni());
        }

        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setDni(dto.getDni());

        Socio guardado = socioRepository.save(socio);
        return convertirADto(guardado);
    }

    public void eliminarSocio(Long id) {
        Socio socio = obtenerSocio(id);

        if (!socio.getPrestamos().isEmpty()) {
            throw new ReglaNegocioException(
                    "No se puede eliminar el socio con id: " + id
                    + " porque tiene préstamos asociados");
        }

        socioRepository.delete(socio);
    }

    private Socio obtenerSocio(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Socio no encontrado con id: " + id));
    }

    private ResponseSocioDto convertirADto(Socio socio) {
        String nombreCompleto = socio.getNombre() + " " + socio.getApellido();

        return new ResponseSocioDto(nombreCompleto, socio.getDni());
    }
}