package com.github.eltonsandre.app.jfx.property;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * @author eltonsandre
 * date: 3 de nov de 2017 15:42:42
 */
@Slf4j
@Data
@NoArgsConstructor
public class ItemSelected {

    private String label;
    private Object value;
    private Class<?>[] params;
    private List<String> paramsName;

    /**
     * Construtor
     * @param label
     * @param value
     */
    public ItemSelected(final String label, final Object value) {
        super();
        this.label = label;
        this.value = value;

        if (this.value instanceof Method) {
            this.params = ((Method) this.value).getParameterTypes();
            if (this.params.length > 0) {
                final var parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames((Method) value);
                this.paramsName = Arrays.asList(parameterNames);
            }
        }
    }

    /**
     * @return boolean
     */
    public boolean isParams() {
        return (this.params != null) && (this.params.length > 0);
    }

    /**
     * @return boolean
     */
    public boolean isNotParams() {
        return !this.isParams();
    }

}