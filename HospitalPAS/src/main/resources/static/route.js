function getScorePopoverContent(constraint_list) {
    var popover_content = "";
    constraint_list.forEach((constraint) => {
        if (getHardScore(constraint.score) === 0) {
            popover_content = popover_content + constraint.name + " : " + constraint.score + "<br>";
        } else {
            popover_content = popover_content + "<b>" + constraint.name + " : " + constraint.score + "</b><br>";
        }
    })
    return popover_content;
}

function getRequiredEquipmentIndictment(designation, indictmentMap){
    const requiredEquipmentIndictment = [];

    designation.patientAdmission.patient.requiredEquipments.forEach(equipment => {
        let indictment = indictmentMap[`requiredEquipment${equipment.id}`]

        if (indictment != null) {
            requiredEquipmentIndictment.push(indictment);
        }
    });

    return requiredEquipmentIndictment;
}

function getPreferredEquipmentIndictment(designation, indictmentMap){
    const preferredEquipmentIndictment = [];

    designation.patientAdmission.patient.preferredEquipments.forEach(equipment => {
        let indictment = indictmentMap[`preferredEquipment${equipment.id}`]

        if (indictment != null) {
            preferredEquipmentIndictment.push(indictment);
        }
    });

    return preferredEquipmentIndictment;
}

function getHardScore(score) {
    return Number(score.slice(0,score.indexOf("hard")))
}

function getSoftScore(score) {
    return Number(score.slice(score.indexOf("/")+1,score.indexOf("soft")))
}

function getTotalScore(indictments)
{
    let hardScore = 0;
    let softScore = 0;
    let matchCount = 0;

    indictments.forEach(indictment => {
        hardScore += getHardScore(indictment.score);
        softScore += getSoftScore(indictment.score);

        matchCount = 0;
    });

    return `Total score: <b>${hardScore}hard/${softScore}soft</b> (${matchCount})<br>`
}

function getBedDesignationBadge(designation, indictmentMap) {
    let indictments = []

    if (indictmentMap[`bedDesignation${designation.id}`] != null) {
        indictments.push(indictmentMap[`bedDesignation${designation.id}`])
    }

    indictments = [...indictments, ...getRequiredEquipmentIndictment(designation, indictmentMap)];
    indictments = [...indictments, ...getPreferredEquipmentIndictment(designation, indictmentMap)];

    let hardScore = 0;
    let softScore = 0;

    indictments.forEach(indictment => {
        hardScore += getHardScore(indictment.score);
        softScore += getSoftScore(indictment.score);
    });

    if (hardScore < 0) return "badge bg-danger"
    if (softScore < 0) return "badge bg-warning"

    return "badge bg-success"
}

function getBedDesignationPopoverContent(designation, indictmentMap) {
    let popover_content = "";
    let indictments = []

    if (indictmentMap[`bedDesignation${designation.id}`] != null) {
        indictments.push(indictmentMap[`bedDesignation${designation.id}`])
    }

    indictments = [...indictments, ...getRequiredEquipmentIndictment(designation, indictmentMap)];
    indictments = [...indictments, ...getPreferredEquipmentIndictment(designation, indictmentMap)];

    console.log(indictments)

    popover_content = getTotalScore(indictments);

    if (indictments.length > 0) {
        indictments.forEach(indictment => indictment.constraintMatches.forEach((match) => {
            if (getHardScore(match.score) === 0) {
                popover_content = popover_content + match.constraintName + " : " + match.score + "<br>";
            } else {
                popover_content = popover_content + "<b>" + match.constraintName + " : " + match.score + "</b><br>";
            }
        }))
    }
    return popover_content;
}

function getBedDesignationLabel(designation) {
    let label ="";

    label += `Patient ${designation.patientAdmission.patient.name} 
        (nights:  ${designation.patientAdmission.arrivalNight}-${designation.patientAdmission.departureNight})`;

    return label;
}

$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const solutionId = urlParams.get('id');

    $.getJSON("/routes/score?id=" + solutionId, function(analysis) {
        var badge = "badge bg-danger";

        if (getHardScore(analysis.score) === 0) { badge = "badge bg-success"; }

        $("#score_a").attr({"title":"Score Brakedown","data-bs-content":"" + getScorePopoverContent(analysis.constraints) + "","data-bs-html":"true"});
        $("#score_text").text(analysis.score);
        $("#score_text").attr({"class":badge});
    });

    $.getJSON("/routes/solution?id=" + solutionId, function(solution) {
        $.getJSON("/routes/indictments?id=" + solutionId, function(indictments) {
            renderRoutes(solution, indictments);
            $(function () {
                $('[data-toggle="popover"]').popover()
            })
        })
    });

});

function renderRoutes(solution, indictments) {
    var indictmentMap = {};
    indictments.forEach((indictment) => {
        indictmentMap[indictment.indictedObjectID] = indictment;
    })

    const bedDesignation_container = $("#bed_designation_container");

    solution.bedDesignations.forEach((designation) => {
        let designation_badge = getBedDesignationBadge(designation, indictmentMap);

        if (indictmentMap[`bedDesignation${designation.id}`] == null){ }

        const hover_content = getBedDesignationPopoverContent(designation, indictmentMap)
        const label = getBedDesignationLabel(designation)

        const html = $(`
            <a data-toggle="popover" data-bs-html="true" data-bs-content="${hover_content}" data-bs-original-title="${label}">
                <span class="${designation_badge}">${label}</span>
            </a>`
        )

        bedDesignation_container.append(html)
    })
}