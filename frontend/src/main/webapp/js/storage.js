const sensorsObservableObjects = [];

const createSensorsSnapshotState = (state) => {
    function render() {
        sensorsObservableObjects.forEach((observableObject) =>{
            observableObject.handleNewStateCallback(state);
        });
    }

    return new Proxy(state, {
        set(target, property, value) {
            target[property] = value;
            render();
            return true;
        }
    });
};

function registerSensorsSnapshotObservableObject(observableObject) {
    sensorsObservableObjects.push(observableObject);
}
const sensorsSnapshotState = createSensorsSnapshotState({});