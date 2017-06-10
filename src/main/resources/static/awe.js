var client = new XMLHttpRequest();

client.onerror = function(e) {
  console.log("Error: " + e.error);
};

client.onload = function() {
  if (!this.response) return;
  var resp = JSON.parse(this.response);
  if (resp.status / 100 != 2) {
    console.log(resp.path + ": " + resp.status + " " + resp.error);
  }
};



function stackTrace() {
  return new Error().stack;
}



function elt(id) {
  var result = document.getElementById(id);
  if (!result) {
    console.log("Could not find element with id " + id);
  }
  return result;
}

function val(id) {
  var element = elt(id);
  return element ? element.value : null;
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

function modalKeyUp(evt) {
  evt = evt || window.event;
  var charCode = evt.keyCode || evt.which;
  if (charCode == 27) {
    cancel();
  }
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
  modal("/work-types", null, null, typeChanged);
}

function setOptions(id, options) {
  var optionsHtml = "";
  var items = options ? JSON.parse(options) : {};
  for(var i = 0; i < items.length; i++) {
    var item = items[i];
    optionsHtml = optionsHtml + "<option value='" + item + "'>" + item + "</option>"; 
  }
  elt(id).innerHTML = optionsHtml;
}

function typeChanged() {
  var type = val("type");
  if (!type) return;
  
  client.onload = function() {
    setOptions("category", this.response);
    categoryChanged();
  } 
  client.open("GET", "/work-types/" + type);
  client.setRequestHeader("Accept", "application/json");
  client.send();
}

function categoryChanged() {
  var type = val("type");
  if (!type) return;
  var category = val("category");
  if (!category) return;
  
  client.onload = function() {
    setOptions("subcategory", this.response);
  } 
  client.open("GET", "/work-types/" + type + "/" + category)
  client.setRequestHeader("Accept", "application/json");
  client.send();
}
