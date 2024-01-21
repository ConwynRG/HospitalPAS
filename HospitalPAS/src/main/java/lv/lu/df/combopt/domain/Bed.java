package lv.lu.df.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Bed {
    private Room room;
    private int number;

    @Override
    public String toString() {
        return String.format("%s (%d)", room.toString(), number);
    }
}
