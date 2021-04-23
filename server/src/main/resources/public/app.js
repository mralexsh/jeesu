
function connect(handler) {
    const socket = new SockJS('/emulator')
    const stompClient = Stomp.over(socket)
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/sensors/polling', function (snapshot) {
            handler(JSON.parse(snapshot.body));
        });
    });
}

connect((s) => {
    function makeSensor(sensor) {
        if (sensor.type === "DIGITAL") {
            return `
                    <div class="level-item">
                        <div class="box">
                        <div class="notification is-link p-3"><strong>${sensor.name}</strong></div>
                        <div class="discreete-body ${sensor.value === 1 ? "discreete-set": "discreete-unset"}" />
                        </div>
                    </div>
                     `;
        } else {
            return `
                     <div class="level-item">
                         <div class="box">
                         <div class="notification is-link p-3"><strong>${sensor.name}</strong></div>
                         <div class="analog-body">
                            <div class="analog-body-percentage" style="width: ${sensor.value}%"/>
                         </div>
                         </div>
                     </div>
                     `;
        }
    }
    function makeNodeItem(item) {
            return `
                 <li class="node-item">
                    <div class="notification is-primary"><strong>${item.nodeName} ${new Date(item.timestamp)}</strong></div>
                    <div class="level-left">${item.sensorsInfo.map(sensor => makeSensor(sensor)).join("")}</div>
                </li>
             `;
    }

    function makeNodes(nodes) {
        return `
             <div class="container box">
             <ul class="nodes-list">
                ${nodes.map(node => makeNodeItem(node)).join("")}
            </ul>
            </div>
         `;
    }

    function makeMainSnapshotContainer(snapshot) {
        const timestamp = new Date(snapshot.aggregationTimestamp);
        return `
            <h4 class="title">MONITORING: ${timestamp}</h4>
            ${makeNodes(snapshot.nodes)}
         `;
    }

    $("#main-content").html(makeMainSnapshotContainer(s))
})

