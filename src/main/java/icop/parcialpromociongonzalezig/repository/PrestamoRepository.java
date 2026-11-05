package icop.parcialpromociongonzalezig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import icop.parcialpromociongonzalezig.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    boolean existsByTituloLibroAndDevueltoFalse(String tituloLibro);
    
}
