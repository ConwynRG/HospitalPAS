package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope = Night.class, property = "number", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Night {
    private int number;

    @Override
    public String toString() {
        return Integer.toString(getNumber());
    }
}
