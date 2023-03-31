// Call the dataTables jQuery plugin
$(document).ready(function () {
    loadUsers()
    $('#users').DataTable();
});

async function loadUsers() {
    const request = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
    });
    const users = await request.json();

    let listHtml = ''
    for (let user of users) {
        let deleteButton = '<a href="#" onclick="deleteUser(' + user.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let phoneNumber = user.phoneNumber == null ? '-' : user.phoneNumber;
        let userHtml = '<tr>\n' +
            '                                            <td>' + user.id + '</td>\n' +
            '                                            <td>' + user.name + ' ' + user.lastname + '</td>\n' +
            '                                            <td>' + user.email + '</td>\n' +
            '                                            <td>' + phoneNumber + '</td><td>' + deleteButton + '</td></tr>'
        listHtml += userHtml;
    }

    document.querySelector("#users tbody").outerHTML = listHtml;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

async function deleteUser(id) {

    if (!confirm('¿Desea eliminar este usuario?')) {
        return;
    }

    const request = await fetch('api/users/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload();
}