
let fetchedDrones;
var map;
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

function populateModelNames(){
    fetch("http://localhost:8080/droneapp/api/models", {mode: "cors"})
        .then((response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Fetch statusTypes failed.")
            }
        })
        .then(data => {
            console.log(data);
            populateModelNameSelect(data)
        })
}
function populateModelNameSelect(data){
    let select = document.getElementById("selectModel");

    for(let i = 0; i < data.length; i++) {
        let opt = data[i];
        let el = document.createElement("option");
        el.textContent = opt.name;
        el.value = opt.name;
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

    const modelSelect = document.getElementById("selectModel");
    if (0 < modelSelect.selectedIndex){
        droneUrl.searchParams.append("modelName", modelSelect.value);
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
            setUpMap(data);
        })
}
function loadDroneDataInTable(data){
    document.getElementById("dronesTableBody").innerHTML = data.map(value => {
        return (
            `<tr>
                <td>${value.registration}</td>
                <td>${value.status}</td>
                <td>${value.model.name}</td>
                <td>${value.sensors.map(sensor => {
                return sensor.sensorType;
            }).join(',')}</td>
                <td>${value.weightKg}</td>
                <td>${value.latitude}</td>
                <td>${value.longitude}</td>
            </tr>`
        );
    }).join('');
}

function setUpMap(data){
    if (map !== undefined) {
        map.off();
        map.remove(); }
    let lon = -84.38633;
    let lat = 33.753746;
    if (0 < data.length){
        lat = data[0].latitude;
        lon = data[0].longitude;
    }
    map = L.map('map').setView([lat, lon], 13);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    data.forEach(drone =>{
        const marker = L.marker([drone.latitude, drone.longitude]).addTo(map);
        marker.bindPopup(drone.registration).openPopup();
    })
}

function mapDrones(data){

}