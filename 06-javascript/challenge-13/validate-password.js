function validatePassword(password) {
    let result = {
        isValid: true,
        score: 0,
        errors: [],
        suggestions: []
    };

    if (password.length < 8) {
        result.isValid = false;
        result.errors.push("Too short");
        result.suggestions.push("Use at least 8 characters");
    } else {
        result.score += 20;
    }

  
    let hasUpper = false;
    for (let c of password) {
        if (c >= 'A' && c <= 'Z') {
            hasUpper = true;
            break;
        }
    }
    if (!hasUpper) {
        result.isValid = false;
        result.errors.push("Missing uppercase letter");
        result.suggestions.push("Add an uppercase letter");
    } else {
        result.score += 20;
    }

  
    let hasLower = false;
    for (let c of password) {
        if (c >= 'a' && c <= 'z') {
            hasLower = true;
            break;
        }
    }
    if (!hasLower) {
        result.isValid = false;
        result.errors.push("Missing lowercase letter");
        result.suggestions.push("Add a lowercase letter");
    } else {
        result.score += 20;
    }


    let hasNumber = false;
    for (let c of password) {
        if (c >= '0' && c <= '9') {
            hasNumber = true;
            break;
        }
    }
    if (!hasNumber) {
        result.isValid = false;
        result.errors.push("Missing number");
        result.suggestions.push("Add a number");
    } else {
        result.score += 20;
    }

   
    const specials = "!@#$%^&*()_+-=";
    let hasSpecial = false;

    for (let c of password) {
        if (specials.includes(c)) {
            hasSpecial = true;
            break;
        }
    }
    if (!hasSpecial) {
        result.isValid = false;
        result.errors.push("Missing special character");
        result.suggestions.push("Add a special character");
    } else {
        result.score += 20;
    }

    
    const commonPasswords = ["password", "123456", "qwerty", "admin"];
    if (commonPasswords.includes(password.toLowerCase())) {
        result.isValid = false;
        result.errors.push("Common password");
        result.suggestions.push("Avoid common passwords");
        result.score -= 20;
    }

  
    if (result.score < 0) result.score = 0;
    if (result.score > 100) result.score = 100;

    return result;
}

console.log(validatePassword('abc'));


console.log(validatePassword('MyP@ssw0rd!2024'));