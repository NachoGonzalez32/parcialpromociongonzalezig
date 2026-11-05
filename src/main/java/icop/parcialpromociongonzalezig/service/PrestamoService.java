package icop.parcialpromociongonzalezig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import icop.parcialpromociongonzalezig.dto.RequestPrestamoDto;
import icop.parcialpromociongonzalezig.dto.ResponsePrestamoDto;
import icop.parcialpromociongonzalezig.entity.Prestamo;
import icop.parcialpromociongonzalezig.entity.Socio;
import icop.parcialpromociongonzalezig.exception.RecursoNoEncontradoException;
import icop.parcialpromociongonzalezig.exception.ReglaNegocioException;
import icop.parcialpromociongonzalezig.repository.PrestamoRepository;
import icop.parcialpromociongonzalezig.repository.SocioRepository;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final SocioRepository socioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           SocioRepository socioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.socioRepository = socioRepository;
    }

    @Transactional
    public ResponsePrestamoDto registrarPrestamo(RequestPrestamoDto dto) {

        Socio socio = obtenerSocio(dto.getSocioId());

        if (prestamoRepository.existsByTituloLibroAndDevueltoFalse(dto.getTituloLibro())) {
            throw new ReglaNegocioException(
                    "El libro " + dto.getTituloLibro() + " ya se encuentra prestado");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setSocio(socio);
        prestamo.setTituloLibro(dto.getTituloLibro());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setDevuelto(false);

        Prestamo guardado = prestamoRepository.save(prestamo);

        return convertirADto(guardado);
    }

    @Transactional(readOnly = true)
    public List<ResponsePrestamoDto> listarPrestamosPorSocio(Long id) {

        Socio socio = obtenerSocio(id);

        return socio.getPrestamos().stream()
                .map(this::convertirADto)
                .toList();
    }

    private Socio obtenerSocio(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Socio no encontrado con id: " + id));
    }

    private ResponsePrestamoDto convertirADto(Prestamo prestamo) {

        Socio socio = prestamo.getSocio();
        String nombreSocio = socio.getNombre() + " " + socio.getApellido();

        return new ResponsePrestamoDto(
                prestamo.getId(),
                prestamo.getTituloLibro(),
                prestamo.getFechaPrestamo(),
                prestamo.isDevuelto(),
                nombreSocio
        );
    }
}