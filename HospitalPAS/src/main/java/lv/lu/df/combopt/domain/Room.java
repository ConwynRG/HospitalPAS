package lv.lu.df.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Room {
    public enum RoomGender {
        MALE, FEMALE, SAME_GENDER, NONE;

        private static final Random random = new Random();

        public static RoomGender randomRoomType()  {
            RoomGender[] roomTypes = values();

            return roomTypes[random.nextInt(roomTypes.length)];
        }
    }

    private String name;
    private Department department;
    private int capacity;
    private RoomGender roomGender;

    private List<Bed> beds;
    private List<RoomEquipment> roomEquipments;

    @Override
    public String toString() {
        return getName();
    }
}
