package com.github.eltonsandre.app.core.domain.mapper;

import com.github.eltonsandre.app.core.domain.dto.ProdutoDTO;
import com.github.eltonsandre.app.core.domain.model.Produto;
import org.mapstruct.Mapper;

/**
 * @author eltonsandre
 * date 03/05/2020 18:27
 */
@Mapper(componentModel = BaseMapper.componentModelSpring)
public interface ProdutosMapper extends BaseMapper<Produto, ProdutoDTO> {

}
