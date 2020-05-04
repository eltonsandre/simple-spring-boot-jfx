package com.github.eltonsandre.app.core.domain.service;

import static com.github.eltonsandre.app.core.domain.validation.ValidationGroups.Insert;
import static com.github.eltonsandre.app.core.domain.validation.ValidationGroups.Upadate;

import com.github.eltonsandre.app.core.domain.dto.ProdutoDTO;
import com.github.eltonsandre.app.core.domain.mapper.ProdutosMapper;
import com.github.eltonsandre.app.core.domain.repository.ProdutosRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author eltonsandre
 * date 03/05/2020 18:22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutosRepository repository;
    private final ProdutosMapper mapper;

    public void save(@Validated({ Insert.class, Upadate.class }) final ProdutoDTO produto) {
        this.repository.save(this.mapper.toEntity(produto));
    }

    public Set<ProdutoDTO> findAll() {
        return this.repository.findAll().parallelStream().map(this.mapper::toDto).collect(Collectors.toSet());
    }
}
