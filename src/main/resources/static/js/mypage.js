

const userInfo = JSON.parse(window.localStorage.getItem('data')) 
document.title = `My page | ${userInfo.firstName}`
document.querySelector('#user-name').textContent = userInfo.firstName;
document.querySelector('#user-email').textContent = userInfo.email;


/**
 * @param {Array<inputHtmlElements>} children
 */
function setContainer(children = []) {
  const form = createElement('div',  {id: "current-form"}, "", children);
  document.querySelector('#form-container').appendChild(form);
}

document.querySelector('#add-car-btn')
  .addEventListener('click', () => {
    const form = document.querySelector('#current-form');
    if(document.body.contains(form)) {
      form.remove();
    }

    setContainer([
      createElement(
        'input', {id:'license-plate-nr-input', placeholder: 'licens plate nr' } 
      ),
      createElement(
        'input', {id:'model-input', placeholder: 'model' }
      ),
      createElement(
        'input', {id:'submit-btn', type: 'submit' }
      )
    ])

    document.querySelector('#submit-btn').addEventListener('click', AddCarInUser) 
  })

document.querySelector('#create-parking-event-btn')
  .addEventListener('click', ()=> {
    const form = document.querySelector('#current-form');
    if(document.body.contains(form)) {
     form.remove();
    }
  
    setContainer([
      createElement(
        'input', {id:'', placeholder: 'start time' } 
      ),
      createElement(
        'input', {id:'', placeholder: 'end time' }
      ),
      createElement(
        'input', {id:'', placeholder: 'select car' }
      ),
      createElement(
        'input', {id:'submit-btn', type: 'submit' }
      )
    ])
    document.querySelector('#submit-btn').addEventListener('click', startEvent)
  })


async function AddCarInUser(){
  // console.log(localStorage.getItem("token"))
  const body = {
    licensePlateNr: document.querySelector("#license-plate-nr-input").value.toUpperCase(),
    model: document.querySelector("#model-input").value,
    email: JSON.parse(localStorage.getItem("data")).email
  }
  const req = await fetch("http://localhost:8080/api/users", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "Authorization" : `Bearer ${localStorage.getItem("token")}`
    },
    body:JSON.stringify(body)
  });
  const data = await req.json();
  console.log(data)
}

/**
 * 
 * @param {string} tagName
 * @param {string} innerText
 * @param {object} attr
 * @param {HtmlElement[]} children
 * @returns html element 
 */
function createElement(tagName, attr= {class:""}, innerText="", children = []) {
  const el = document.createElement(tagName);
  // el.setAttribute('id', elID)
  el.textContent = innerText;

  Object.entries(attr).forEach(attr => {
    el.setAttribute(attr[0], attr[1])
  })

  if(children.length > 0) {
    children.forEach(child => {
      el.appendChild(child) 
    });
  } 
  
  return el;
}
