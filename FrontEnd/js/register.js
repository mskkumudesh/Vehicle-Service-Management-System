async function register() {

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;


    if (!name || !email || !username || !password) {
        alert("Please fill in all fields");
        return;
    }


    const userData = {
        name: name,
        email: email,
        username: username,
        password: password,
    };

    try {

        const response = await fetch('http://localhost:8080/api/v1/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });


        const result = await response.json();

        if (response.ok) {
            alert("Registration Successful!");
            window.location.href = "/FrontEnd/pages/login.html";
        } else {
            alert("Registration Failed: " + result.message);
        }

    } catch (error) {
        console.error("Error:", error);
        alert("Could not connect to server.");
    }
}
