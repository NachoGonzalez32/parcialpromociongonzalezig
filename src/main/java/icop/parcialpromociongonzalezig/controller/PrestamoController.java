package icop.parcialpromociongonzalezig.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import icop.parcialpromociongonzalezig.dto.RequestPrestamoDto;
import icop.parcialpromociongonzalezig.dto.ResponsePrestamoDto;
import icop.parcialpromociongonzalezig.service.PrestamoService;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<ResponsePrestamoDto> registrarPrestamo(
            @RequestBody RequestPrestamoDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prestamoService.registrarPrestamo(dto));
    }

    @GetMapping("/socio/{id}")
    public ResponseEntity<List<ResponsePrestamoDto>> listarPrestamosPorSocio(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                prestamoService.listarPrestamosPorSocio(id)
        );
    }
}