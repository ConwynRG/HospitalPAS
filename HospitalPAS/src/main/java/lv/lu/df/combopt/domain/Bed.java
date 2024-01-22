package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = Bed.class, property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Bed {
    private int id;

    @JsonIdentityReference
    private Room room;
    private int number;

    @Override
    public String toString() {
        return String.format("%s (%d)", room.toString(), number);
    }
}
