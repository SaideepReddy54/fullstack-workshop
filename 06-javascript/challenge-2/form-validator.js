const form = document.getElementById("registerForm");
const inputs = form.querySelectorAll("input");
const submitBtn = form.querySelector("button");

const username = document.getElementById("username");
const email = document.getElementById("email");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");
function showError(input, message) {
    const error = input.nextElementSibling;
    error.textContent = message;
    input.classList.remove("valid");
    input.classList.add("invalid");
}

function showSuccess(input) {
    const error = input.nextElementSibling;
    error.textContent = "";
    input.classList.remove("invalid");
    input.classList.add("valid");
}

function validateUsername() {
    const value = username.value.trim();
    const regex = /^[a-zA-Z0-9]{3,15}$/;

    if (!regex.test(value)) {
        showError(username, "3–15 chars, letters & numbers only");
        return false;
    }
    showSuccess(username);
    return true;
}

function validateEmail() {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regex.test(email.value.trim())) {
        showError(email, "Enter a valid email");
        return false;
    }
    showSuccess(email);
    return true;
}

function validatePassword() {
    const regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;
    if (!regex.test(password.value)) {
        showError(password, "8+ chars, 1 uppercase, 1 number, 1 special char");
        return false;
    }
    showSuccess(password);
    return true;
}

function validateConfirmPassword() {
    if (confirmPassword.value !== password.value || confirmPassword.value === "") {
        showError(confirmPassword, "Passwords do not match");
        return false;
    }
    showSuccess(confirmPassword);
    return true;
}
function checkFormValidity() {
    const isValid =
        validateUsername() &&
        validateEmail() &&
        validatePassword() &&
        validateConfirmPassword();

    submitBtn.disabled = !isValid;
}
username.addEventListener("blur", validateUsername);
email.addEventListener("blur", validateEmail);
password.addEventListener("blur", validatePassword);
confirmPassword.addEventListener("blur", validateConfirmPassword);

inputs.forEach(input =>
    input.addEventListener("input", checkFormValidity)
);

form.addEventListener("submit", (e) => {
    e.preventDefault();
    if (!submitBtn.disabled) {
        alert("Registration Successful!");
        form.reset();
        submitBtn.disabled = true;
        inputs.forEach(i => i.classList.remove("valid"));
    }
});
