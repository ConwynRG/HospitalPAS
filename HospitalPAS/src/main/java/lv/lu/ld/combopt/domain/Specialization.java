package lv.lu.ld.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Specialization {
    private String name;

    @Override
    public String toString() {
        return getName();
    }
}
