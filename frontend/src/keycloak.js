import Keycloak from "keycloak-js";

const _kc = new Keycloak({
   url: "http://localhost:8000",
   realm: "sharkPark",
   clientId: "sharkParkClient"
  });
   
const initKeycloak = (renderApp) => {
  _kc.init({
    onload: 'login-required',
  }).then((authenticated) => {
    if(authenticated) {
      window.localStorage.setItem("name", _kc.tokenParsed["given_name"]);
      window.localStorage.setItem("email", _kc.tokenParsed["email"]);

      renderApp();
    } else {
      console.warn("not authed");
      _kc.login();
    }
  })
}

const getToken = () => _kc.token;
const doLogout = () => _kc.logout;
const doLogin = () => _kc.login;
const LoginService = {
  initKeycloak,
  doLogin,
  doLogout,
  getToken
}

export default LoginService;
