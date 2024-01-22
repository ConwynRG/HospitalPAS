package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = PreferredEquipment.class, property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class PreferredEquipment {
    private int id;

    @JsonIdentityReference
    private Patient patient;

    @JsonIdentityReference
    private Equipment equipment;

    @Override
    public String toString() {
        return patient.toString() + " - " + equipment.toString();
    }
}
