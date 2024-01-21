package lv.lu.df.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Department {
    private String name;
    private Specialization specialization;
    private List<Room> rooms;

    @Override
    public String toString() {
        return getName();
    }
}
