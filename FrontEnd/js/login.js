async function login() {
    let email = document.getElementById("email").value.trim();
    let pass = document.getElementById("password").value.trim();

    if (email === "" || pass === "") {
        alert("Please fill all fields");
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/api/v1/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({email:email, password: pass })
        });

        const result = await response.json();
        const Access_token =result.data.access_token
        console.log(Access_token);

        if (response.ok) {
            console.log(JSON.stringify(result.data));
            // result.data contains the AuthResponseDTO
            localStorage.setItem("token", Access_token);

            const userRole = result.data.role;

            if (userRole === "Customer") {
                window.location.href = "/FrontEnd/pages/customer-dashboard.html";
            } else if (userRole === "Admin") {
                window.location.href = "/FrontEnd/pages/admin/dashboard.html";
            }
        } else {
            alert("Login Failed: " + result.message);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Could not connect to server.");
    }

}
