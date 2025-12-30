const form = document.getElementById("registerForm");
const inputs = form.querySelectorAll("input");
const submitBtn = form.querySelector("button");

const username = document.getElementById("username");
const email = document.getElementById("email");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");

const showError = (input, message) => {
    const error = input.nextElementSibling;
    error.textContent = `${message}`; // ✅ template literal
    input.classList.add("invalid");
    input.classList.remove("valid");
};

const showSuccess = (input) => {
    const error = input.nextElementSibling;
    error.textContent = ``; // ✅ template literal
    input.classList.add("valid");
    input.classList.remove("invalid");
};

const validateUsername = () => {
    const regex = /^[a-zA-Z0-9]{3,15}$/;
    if (!regex.test(username.value.trim())) {
        showError(username, "3–15 chars, letters & numbers only");
        return false;
    }
    showSuccess(username);
    return true;
};

const validateEmail = () => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regex.test(email.value.trim())) {
        showError(email, "Enter a valid email");
        return false;
    }
    showSuccess(email);
    return true;
};

const validatePassword = () => {
    const regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;
    if (!regex.test(password.value)) {
        showError(password, "8+ chars, 1 uppercase, 1 number, 1 special char");
        return false;
    }
    showSuccess(password);
    return true;
};

const validateConfirmPassword = () => {
    if (confirmPassword.value !== password.value || confirmPassword.value === "") {
        showError(confirmPassword, "Passwords do not match");
        return false;
    }
    showSuccess(confirmPassword);
    return true;
};

const checkFormValidity = () => {
    const isValid =
        validateUsername() &&
        validateEmail() &&
        validatePassword() &&
        validateConfirmPassword();

    submitBtn.disabled = !isValid;
};

inputs.forEach(input => input.addEventListener("input", checkFormValidity));

form.addEventListener("submit", (e) => {
    e.preventDefault();
    if (!submitBtn.disabled) {
        alert("Registration Successful!");
        form.reset();
        submitBtn.disabled = true;
        inputs.forEach(i => i.classList.remove("valid"));
    }
});
