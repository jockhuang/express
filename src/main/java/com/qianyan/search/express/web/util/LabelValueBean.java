/**
 * 
 */
package com.qianyan.search.express.web.util;

/**
 * @author jock
 */
public final class LabelValueBean implements java.io.Serializable {
    private static final long serialVersionUID = 37189186300891958L;
    private String            label;

    private Object            value;

    public LabelValueBean(final String label, final Object value) {
        this.label = label;
        this.value = value;
    }

    /**
     * @return the label
     */
    public final String getLabel() {
        return label;
    }

    /**
     * @return the value
     */
    public final Object getValue() {
        return value;
    }

    /**
     * @param label
     *            the label to set
     */
    public final void setLabel(final String label) {
        this.label = label;
    }

    /**
     * @param value
     *            the value to set
     */
    public final void setValue(final Object value) {
        this.value = value;
    }

}
