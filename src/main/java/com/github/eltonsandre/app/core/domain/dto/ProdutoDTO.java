package com.github.eltonsandre.app.core.domain.dto;

import com.github.eltonsandre.app.core.domain.validation.ValidationGroups;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author eltonsandre
 * date 03/05/2020 18:10
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProdutoDTO {

    @NotBlank(groups = { ValidationGroups.Insert.class, ValidationGroups.Upadate.class, ValidationGroups.Delete.class })
    String codigo;

    @NotBlank(groups = { ValidationGroups.Insert.class, ValidationGroups.Upadate.class })
    String descricao;

    @NotBlank(groups = { ValidationGroups.Insert.class, ValidationGroups.Upadate.class })
    BigDecimal valor;

}
