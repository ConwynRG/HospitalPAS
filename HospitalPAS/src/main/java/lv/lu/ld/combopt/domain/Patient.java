package lv.lu.ld.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Patient {
    public enum PatientGender {MALE, FEMALE}

    private String name;
    private PatientGender gender;
    private int preferredMaxRoomSize;

    private List<RequiredEquipment> requiredEquipments;
    private List<PreferredEquipment> preferredEquipments;

    @Override
    public String toString() {
        return getName();
    }
}
