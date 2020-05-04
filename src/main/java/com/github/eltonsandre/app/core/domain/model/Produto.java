package com.github.eltonsandre.app.core.domain.model;

import com.github.eltonsandre.app.core.domain.validation.ValidationGroups;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

/**
 * @author eltonsandre
 * date 03/05/2020 18:05
 */
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @NotBlank
    @EqualsAndHashCode.Include
    @NotBlank(groups = { ValidationGroups.Insert.class, ValidationGroups.Upadate.class,
            ValidationGroups.Delete.class })
    String codigo;

    @NotBlank(groups = { ValidationGroups.Insert.class, ValidationGroups.Upadate.class })
    String descricao;

    @NotBlank(groups = { ValidationGroups.Insert.class, ValidationGroups.Upadate.class })
    BigDecimal valor;

}
