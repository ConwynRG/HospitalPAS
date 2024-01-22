package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.constraint.ConstraintMatch;
import lombok.Getter;
import lombok.Setter;
import lv.lu.df.combopt.domain.BedDesignation;
import lv.lu.df.combopt.domain.PreferredEquipment;
import lv.lu.df.combopt.domain.RequiredEquipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
public class SimpleIndictmentObject {

    private String indictedObjectID;
    private HardSoftScore score;
    private int matchCount;
    private List<SimpleConstraintMatch> constraintMatches = new ArrayList<>();

    public SimpleIndictmentObject(Object indictedObject, HardSoftScore score, int matchCount, Set<ConstraintMatch<HardSoftScore>> constraintMatches) {
        this.indictedObjectID = getId(indictedObject);
        this.score = score;
        this.matchCount = matchCount;
        this.constraintMatches = constraintMatches.stream().map(SimpleConstraintMatch::new).collect(Collectors.toList());
    }

    private String getId(Object indictedObject) {
        if (indictedObject instanceof BedDesignation) return "bedDesignation"+((BedDesignation) indictedObject).getId();
        if (indictedObject instanceof RequiredEquipment) return "requiredEquipment" + ((RequiredEquipment) indictedObject).getId();
        if (indictedObject instanceof PreferredEquipment) return "preferredEquipment" + ((PreferredEquipment) indictedObject).getId();

        return "";
    }
}
