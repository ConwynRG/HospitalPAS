package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = Specialization.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Specialization {
    private String name;

    @Override
    public String toString() {
        return getName();
    }
}
