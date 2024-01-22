package lv.lu.df.combopt.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = RoomEquipment.class, property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class RoomEquipment {
    private int id;

    @JsonIdentityReference
    private Room room;

    @JsonIdentityReference
    private Equipment equipment;

    @Override
    public String toString() {
        return room.toString() + "-" + equipment.toString();
    }
}
