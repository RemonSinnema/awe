var client = new XMLHttpRequest();

client.onerror = function(e) {
  alert("Error: " + e.error);
};

client.onload = function() {
  if (!this.response) return;
  var resp = JSON.parse(this.response);
  if (resp.status / 100 != 2) {
    alert(resp.path + ": " + resp.status + " " + resp.error);
  }
};



function stackTrace() {
  return new Error().stack;
}



function elt(id) {
  return document.getElementById(id);
}

function val(id) {
  return elt(id).value;
}

function init() {
  elt("title").focus();
}

function ensureText(field) {
  if (!field.value) {
    field.value = 'Enter text'
    field.select()
  }
}

function setStyle(element, visibility, display) {
  element.style.visibility = visibility;
  element.style.display = display;
}

function show(id) {
  setStyle(elt(id), "visible", "block");
}

function hide(id) {
  setStyle(elt(id), "hidden", "none");
}


var modalOkHandler;

function cancel() {
  hide("modal");
}

function ok() {
  if (modalOkHandler) {
    modalOkHandler();
    modalOkHandler = null;
  }
  cancel();
}

function modal(url, okHandler, formLoaded, formShown) {
  client.open("GET", url);
  client.setRequestHeader("Accept", "text/html");
  client.onload = function() {
    elt("modal-content").innerHTML = this.response;
    if (formLoaded) {
      formLoaded();
    }
    modalOkHandler = okHandler;
    show("modal");
    if (formShown) {
      formShown();
    }
  }
  client.send();
}

function ask(question, defaultAnswer, handleAnswered) {
  modal("/static/prompt.html", function () {
    handleAnswered(elt("answer").value);
  }, function() {
    elt("question").textContent = question;
    elt("answer").value = defaultAnswer;
  }, function() {
    var answerField = elt("answer");
    answerField.select();
    answerField.focus();
  });
}

function fileNameFrom(title) {
  return title.toLowerCase().replace(/\s+/g, "-") + ".txt"
}

function titleFrom(fileName) {
  var result = fileName.replace(/-/g, " ");
  result = result.substring(0, result.lastIndexOf('.'));
  result = result.substring(0, 1).toUpperCase() + result.substring(1);
  return result;
}

function save() {
  var title = val("title");
  var text = val("text");
  ask("Save as", fileNameFrom(title), function(fileName) {
    client.onload = null;
    client.open("PUT", "/work/" + fileName);
    client.setRequestHeader("Content-Type", "text/plain");
    client.send(text);
    elt("text").focus();
  });
}

function load() {
  var title = val("title");
  ask("Load from", "", function(fileName) {
    client.onload = function() {
      elt("title").value = titleFrom(fileName);
      elt("text").value = this.response;
    };
    client.open("GET", "/work/" + fileName);
    client.setRequestHeader("Accept", "text/plain");
    client.send();
  });
}

function create() {
  modal("/create");
}
