$(document).ready(function () {
    $.getJSON("/routes/list", function(routes) {
        let listofroutes = $("#listofroutes");

        $.each(routes, function(idx, value) {
            let html = $(`<li>
                            <a href="route.html?id=${value.solutionId}">${value.score}</a>
                            <ul>
                                <li>Patients: ${value.patients.length}</li>
                                <li>Departments: ${value.departments.length}</li>
                                <li>Rooms: ${value.rooms.length}</li>
                                <li>Beds: ${value.beds.length}</li>
                                <li>Nights: ${value.nights.length}</li>
                            </ul>
                        </li>`)

            listofroutes.append(html);
        });
    });
});