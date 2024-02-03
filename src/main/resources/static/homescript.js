
let fetchedDrones;
function getAllDrones(){
    fetch("http://localhost:8080/droneapp/api/drones", {mode: "cors"})
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Fetch drones failed.")
            }
        })
        .then(data => {
            console.log(data);
        })
}

function populateSensorTypes(){
    fetch("http://localhost:8080/droneapp/api/sensorTypes", {mode: "cors"})
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Fetch sensorTypes failed.")
            }
        })
        .then(data => {
            console.log(data);
            populateSensorTypeSelect(data);
        })
}

function populateSensorTypeSelect(data){
    let select = document.getElementById("selectSensor");

    for(let i = 0; i < data.length; i++) {
        let opt = data[i];
        let el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        select.add(el);
    }
}

function populateStatusTypes(){
    fetch("http://localhost:8080/droneapp/api/statusTypes", {mode: "cors"})
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Fetch statusTypes failed.")
            }
        })
        .then(data => {
            console.log(data);
            populateStatusTypeSelect(data)
        })
}

function populateStatusTypeSelect(data){
    let select = document.getElementById("selectStatus");

    for(let i = 0; i < data.length; i++) {
        let opt = data[i];
        let el = document.createElement("option");
        el.textContent = opt;
        el.value = opt;
        select.add(el);
    }
}

function populateDroneTable(){
    const droneUrl = new URL("http://localhost:8080/droneapp/api/drones");

    const statusSelect = document.getElementById("selectStatus");
    if (0 < statusSelect.selectedIndex){
        droneUrl.searchParams.append("status", statusSelect.value);
    }

    const sensorSelect = document.getElementById("selectSensor");
    if (0 < sensorSelect.selectedIndex){
        droneUrl.searchParams.append("sensorType", sensorSelect.value);
    }

    fetch(droneUrl, {mode: "cors"})
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Fetch drones failed. " + response.status)
            }
        })
        .then(data => {
            console.log(data);
            fetchedDrones = data;
            loadDroneDataInTable(fetchedDrones);
        })
}
function loadDroneDataInTable(data){
    const tableData = data.map(value => {
        return (
            `<tr>
                <td>${value.registration}</td>
                <td>${value.status}</td>
                <td>${value.model.name}</td>
                <td>${value.sensors.map(sensor => {return sensor.sensorType;}).join(',')}</td>
                <td>${value.weightKg}</td>
                <td>${value.latitude}</td>
                <td>${value.longitude}</td>
            </tr>`
        );
    }).join('');
    document.getElementById("dronesTableBody").innerHTML = tableData;
}