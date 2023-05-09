let keycloakUrl = "http://localhost:8000";
let keycloakJsSrc = keycloakUrl + "/js/keycloak.js";

var script = document.createElement("script");
script.type = "text/javascript";
script.src = keycloakJsSrc;
document.getElementsByTagName("head")[0].appendChild(script);

window.onload = function () {
  window.keycloak = new Keycloak({
    url: keycloakUrl,
    realm: "sharkPark",
    clientId: "sharkParkClient",
  });

  keycloak
    .init({
      onLoad: "login-required",
      checkLoginIframe: false,
    })
    .then(function (authenticated) {
      if (keycloak.authenticated) {
        showMenu();
        showProfile();
        addUserToDatabase();
      } else {
      }
    })
    .catch(function () {
      alert("failed to initialize");
    });
  keycloak.onAuthLogout = showWelcome;
  keycloak.onAuthLogout = localStorage.removeItem("data");
  keycloak.onAuthLogout = localStorage.removeItem("token");
};

function addUserToDatabase() {
  let firstname = keycloak.tokenParsed["given_name"];
  let lastname = keycloak.tokenParsed["family_name"];
  let email = keycloak.tokenParsed["email"];

  let data = {
    firstName: firstname,
    lastName: lastname,
    email: email,
  };

  window.localStorage.setItem("data", JSON.stringify(data));
  window.localStorage.setItem("token", keycloak.token);

  const url = "http://localhost:8080/api/user";
  const req = new XMLHttpRequest();
  req.open("POST", url, true);
  req.setRequestHeader("Accept", "application/json");
  req.setRequestHeader("Content-Type", "application/json");
  req.setRequestHeader("Authorization", "Bearer " + keycloak.token);
  req.send(JSON.stringify(data));
}

function callRestApi() {
  const url = "http://localhost:8080/api/user";

  const req = new XMLHttpRequest();
  req.open("GET", url, true);
  req.setRequestHeader("Accept", "application/json");
  req.setRequestHeader("Authorization", "Bearer " + keycloak.token);

  req.onreadystatechange = function () {
    if (req.readyState == 4) {
      if (req.status == 200) {
        select("#restapi-content").innerHTML = req.response;
        show("#restapi");
      } else if (req.status == 403) {
        alert("Forbidden");
      }
    }
  };
  req.send();
}

function showMenu() {
  select(".menu").style.display = "block";
}

function showWelcome() {
  show("#welcome");
}

function showProfile() {
  let firstname = keycloak.tokenParsed["given_name"];
  let lastname = keycloak.tokenParsed["family_name"];
  let email = keycloak.tokenParsed["email"];

  if (firstname) {
    select("#firstName").innerHTML = firstname;
  }
  if (lastname) {
    select("#lastName").innerHTML = lastname;
  }
  if (email) {
    select("#email").innerHTML = email;
  }

  show("#profile");
}

function show(selector) {
  select("#profile").style.display = "none";
  select("#restapi").style.display = "none";
  select(selector).style.display = "block";
}

function select(selector) {
  return document.querySelector(selector);
}
