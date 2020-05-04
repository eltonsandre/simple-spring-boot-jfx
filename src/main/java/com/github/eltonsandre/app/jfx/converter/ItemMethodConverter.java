package com.github.eltonsandre.app.jfx.converter;

import com.github.eltonsandre.app.jfx.property.ItemSelected;
import java.lang.reflect.Method;
import javafx.util.StringConverter;

/**
 * @author eltonsandre
 * date 3 de nov de 2017 15:11:54
 */
public class ItemMethodConverter extends StringConverter<ItemSelected> {

    @Override
    public String toString(final ItemSelected t) {
        if (t == null) {
            return "[none]";
        }
        String label = ((Method) t.getValue()).getDeclaringClass().toString().toLowerCase();
        return label;
    }

    /* (non-Javadoc)
     *
     * @see javafx.util.StringConverter#fromString(java.lang.String) */
    @Override
    public ItemSelected fromString(final String string) {
        throw new RuntimeException("not required for non editable ComboBox");
    }

    private String getStringParams(final ItemSelected t) {
        if (t.getParamsName() != null) {
            return t.getParamsName().toString().replaceAll("\\[", "_").replaceAll(", ", "_").replaceAll("\\]", "");
        }
        return "";
    }

}
