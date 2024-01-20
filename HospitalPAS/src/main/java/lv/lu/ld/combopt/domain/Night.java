package lv.lu.ld.combopt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Night {
    private int number;

    @Override
    public String toString() {
        return String.format("Night Nr. %d", getNumber());
    }
}
