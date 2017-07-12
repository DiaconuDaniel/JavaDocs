package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Daniel.Diaconu on 17/07/12.
 */
@Table(name = "location")
public class Location {
    @Id(name="id")
    private long id;

    @Column(name = "streetAddress")
    private String streetAddress;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "stateProvince")
    private String stateProvince;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
        if (streetAddress != null ? !streetAddress.equals(location.streetAddress) : location.streetAddress != null)
            return false;
        if (postalCode != null ? !postalCode.equals(location.postalCode) : location.postalCode != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        return stateProvince != null ? stateProvince.equals(location.stateProvince) : location.stateProvince == null;
    }


}
