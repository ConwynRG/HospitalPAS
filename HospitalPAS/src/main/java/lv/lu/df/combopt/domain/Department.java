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
@JsonIdentityInfo(scope = Department.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Department {
    private String name;
    @JsonIdentityReference
    private Specialization specialization;
    @JsonIdentityReference
    private List<Room> rooms;

    @Override
    public String toString() {
        return getName();
    }
}
