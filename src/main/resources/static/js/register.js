// Call the dataTables jQuery plugin
$(document).ready(function () {

});

async function registerUsers() {
    let data = {};
    data.name = document.getElementById('exampleFirstName').value;
    data.lastname = document.getElementById('exampleLastName').value;
    data.email = document.getElementById('exampleInputEmail').value;
    data.password = document.getElementById('exampleInputPassword').value;

    let repeatPassword = document.getElementById('exampleRepeatPassword').value;

    if (repeatPassword !== data.password){
        alert("Las contraseñas ingresadas son diferentes")
        window.location.href = "register.html";
        return;
    }else{
        window.location.href = "login.html";
    }

    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    alert("La cuenta fue creada con exito!")
}