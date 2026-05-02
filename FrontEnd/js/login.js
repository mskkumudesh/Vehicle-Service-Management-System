async function login() {
    let email = document.getElementById("email").value.trim();
    let pass = document.getElementById("password").value.trim();

    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    const passwordPattern = /^.{6,}$/;

    let emailError = document.getElementById("emailError");
    let passwordError = document.getElementById("passwordError");

    emailError.innerText = "";
    passwordError.innerText = "";

    let isValid = true;

    //
    if (email === "") {
        emailError.innerText = "Email is required";
        isValid = false;
    } else if (!emailPattern.test(email)) {
        emailError.innerText = "Invalid email format";
        isValid = false;
    }

    //
    if (pass === "") {
        passwordError.innerText = "Password is required";
        isValid = false;
    } else if (!passwordPattern.test(pass)) {
        passwordError.innerText = "Password must be at least 6 characters";
        isValid = false;
    }

    //
    if (!isValid) return;

    //
    try {
        const response = await fetch('http://localhost:8080/api/v1/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: email, password: pass })
        });

        const result = await response.json();
        console.log(result);

        if (response.ok) {
            const accessToken = result.data.token;
            localStorage.setItem("token", accessToken);

            const userRole = result.data.role;

            if (userRole === "Customer") {
                window.location.href = "/FrontEnd/pages/customer-dashboard.html";
            } else if (userRole === "Admin") {
                window.location.href = "/FrontEnd/pages/admin/admin-dashboard.html";
            }

        } else {
            passwordError.innerText = result.message ;
        }

    } catch (error) {
        console.error("Error:", error);
        alert("Server connection failed");
    }
}