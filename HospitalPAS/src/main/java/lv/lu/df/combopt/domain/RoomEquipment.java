package lv.lu.df.combopt.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RoomEquipment {
    private Room room;
    private Equipment equipment;

    @Override
    public String toString() {
        return room.toString() + "-" + equipment.toString();
    }
}
