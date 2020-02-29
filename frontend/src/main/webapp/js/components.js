class SensorsSnapshot extends HTMLElement {
    constructor() {
        super();
        registerSensorsSnapshotObservableObject(this);
        this.shadow = this.attachShadow({mode: 'open'});
        this.rootSnapshot = document.createElement('div');
        this.shadow.appendChild(this.rootSnapshot);
        const style = document.createElement('style');
        style.textContent = `
            .discreete-body {
            width: 40px;
            height: 40px;
            border: 3px solid blue;
            border-radius: 50%;
        }
        .discreete-set {
                background: red;
            }
        .discreete-unset {
                background: none;
            }
        .analog-body {
        width: 400px;
            height: 40px;
            min-height: 40px;    
            border: 3px solid blue;    
        } 
        .analog-body-percentage {
        theight:100%;
        min-height:100%;
        background: green;
        }
        `;
        this.shadow.appendChild(style);
    }

    handleNewStateCallback(state) {
        this.rootSnapshot.innerHTML = "<h2>" + state.snapshot.operator + " " + new Date(state.snapshot.timeStamp) + "</h2>";
        this.rootSnapshot.innerHTML += this.renderSensorNodes(state.snapshot.nodes);
    }

    renderSensorNodes(sensorNodes) {
        let res = "<div>";
        const self = this;
        sensorNodes.forEach((sensorNode) => {
            res += self.renderSensorNode(sensorNode);
        });
        res += "</div>";
        return res;
    }

    renderSensorNode(sensorNode) {
        let res = "<div>";
        const self = this;
        res += "<h4>" + sensorNode.node + " " + new Date(sensorNode.timeStamp) + "</h4>";
        sensorNode.sensors.forEach((sensor) => {
            res += self.renderSensor(sensor);
        });
        res += "</div>";
        return res;
    }

    renderSensor(sensor) {
        let res = "<div>";
        res+='<span>' + sensor.name + '</span>';
        if (sensor.type === "DIGITAL") {
            if (sensor.value == 0) {
                res += '<div class="discreete-body discreete-set"></div>'
            } else {
                res += '<div class="discreete-body discreete-unset"></div>'
            }
        } else {
            res += '<div class="analog-body">';
            res += '<div class="analog-body-percentage" style="width: ' + sensor.value + '%">';
            res += "</div>";
            res += "</div>";
        }
        res += "</div>";
        return res;
    }
}


customElements.define('sensors-snapshot', SensorsSnapshot);
