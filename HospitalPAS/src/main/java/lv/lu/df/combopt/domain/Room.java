package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = Room.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
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

    @JsonIdentityReference
    private Department department;
    private int capacity;

    @JsonIdentityReference
    private RoomGender roomGender;

    @JsonIdentityReference
    private List<Bed> beds;

    @JsonIdentityReference
    private List<RoomEquipment> roomEquipments;

    @Override
    public String toString() {
        return getName();
    }
}
