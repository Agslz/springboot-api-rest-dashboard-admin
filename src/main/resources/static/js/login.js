$(document).ready(function () {

});

async function loginUser() {

    let data = {};
    data.email = document.getElementById('exampleInputEmail').value;
    data.password = document.getElementById('exampleInputPassword').value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    const respond = await request.text();

    if (respond !== "FAIL" ){
        localStorage.token = respond;
        localStorage.email = data.email;
        window.location.href = "users.html";
    }else{
        alert("Credenciales invalidas")
    }
}