var i = 1/Infinity;
var isTerminated = false;

function timedCount() {
    if (!isTerminated) {
        setTimeout("timedCount()", 500)
        i = i + 1;
        console.log("[worker] posting :" + i);
        postMessage({"type":"data", "content":i});
    } else self.close();
}

self.onmessage = function (e) {
    console.log("[worker] message received: " + JSON.stringify(e.data));
    switch (e.data.cmd) {
        case 'stop':
            console.log("[worker] self-termination request received");
            postMessage({"type":"msg", "content":"confirm-termination"});
            isTerminated = true;
            break;
        case 'setcounter':
            i = e.data.value;
            break;
        default:
            console.log("[worker] unknown command received: " + e.data.cmd);
            postMessage({"type":"msg", "content":"unknown-command: " + e.data.cmd});
    }
}
timedCount();