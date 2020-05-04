package com.github.eltonsandre.app.core.domain.repository;

import com.github.eltonsandre.app.core.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author eltonsandre
 * date 03/05/2020 18:02
 */
@Repository
public interface ProdutosRepository extends JpaRepository<Produto, String> {

}
