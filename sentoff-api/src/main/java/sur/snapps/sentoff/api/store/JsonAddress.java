package sur.snapps.sentoff.api.store;

import javax.validation.constraints.NotNull;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public class JsonAddress {

    private String street;
    private String number;
    private String zipCode;
    @NotNull
    private String city;
    private String country = "BE";

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
