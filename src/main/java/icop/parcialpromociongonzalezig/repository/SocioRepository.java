package icop.parcialpromociongonzalezig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import icop.parcialpromociongonzalezig.entity.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long> {

    boolean existsByDni(String dni);

}
