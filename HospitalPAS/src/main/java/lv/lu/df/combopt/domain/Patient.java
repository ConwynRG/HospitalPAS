package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = Patient.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Patient {
    public enum PatientGender {MALE, FEMALE}

    private String name;
    private PatientGender gender;
    private int preferredMaxRoomSize;

    @JsonIdentityReference
    private List<RequiredEquipment> requiredEquipments;
    @JsonIdentityReference
    private List<PreferredEquipment> preferredEquipments;

    @Override
    public String toString() {
        return getName();
    }
}
