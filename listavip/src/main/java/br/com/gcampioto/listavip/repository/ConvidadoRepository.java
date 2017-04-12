package br.com.gcampioto.listavip.repository;

import br.com.gcampioto.listavip.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gcampioto on 11/04/17.
 */
public interface ConvidadoRepository extends JpaRepository<Convidado, Long>{
}
