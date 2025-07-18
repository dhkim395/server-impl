function saveToken(token) {
    localStorage.setItem('jwt', token);
}

function getToken() {
    return localStorage.getItem('jwt');
}

function authHeader() {
    return {
        'Authorization': 'Bearer ' + getToken(),
        'Content-Type': 'application/json'
    };
}
