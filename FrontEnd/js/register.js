async function register() {

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();


    ["name", "email", "username", "password"].forEach(field => {
        let el = document.getElementById(field + "Error");
        if (el) el.innerText = "";
    });


    if (!name || !email || !username || !password) {
        alert("Please fill in all fields");
        return;
    }

    const userData = {
        name,
        email,
        username,
        password
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
            console.log(result);
            let passwordError = document.getElementById("passwordError");
            passwordError.innerText = result.message;


            for (let field in result) {
                let errorElement = document.getElementById(field + "Error");

                if (errorElement) {
                    errorElement.innerText = result[field];
                }
            }
        }

    } catch (error) {
        console.error("Error:", error);
        alert("Could not connect to server.");
    }
}