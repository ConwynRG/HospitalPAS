package lv.lu.df.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PreferredEquipment {
    private Patient patient;
    private Equipment equipment;

    @Override
    public String toString() {
        return patient.toString() + " - " + equipment.toString();
    }
}
