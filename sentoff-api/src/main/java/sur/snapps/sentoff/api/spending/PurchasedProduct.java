package sur.snapps.sentoff.api.spending;

import sur.snapps.sentoff.api.validation.AmountValue;

import javax.validation.constraints.NotNull;

/**
 * @author sur
 * @since 30/03/2016
 */
public class PurchasedProduct {

    private String code;
    @NotNull
    private String description;
    @NotNull
    private String type;
    @NotNull
    @AmountValue
    private String unitPrice;
    @NotNull
    private String units;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getUnits() {
        return units;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
