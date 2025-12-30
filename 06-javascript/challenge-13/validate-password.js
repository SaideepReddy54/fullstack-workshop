const validatePassword = (password) => {
    const result = {
        isValid: true,
        score: 0,
        errors: [],
        suggestions: []
    };

    // Length check
    if (password.length < 8) {
        result.isValid = false;
        result.errors.push(`Too short`);
        result.suggestions.push(`Use at least 8 characters`);
    } else {
        result.score += 20;
    }

    // Uppercase check
    const hasUpper = [...password].some(
        c => c >= 'A' && c <= 'Z'
    );
    if (!hasUpper) {
        result.isValid = false;
        result.errors.push(`Missing uppercase letter`);
        result.suggestions.push(`Add an uppercase letter`);
    } else {
        result.score += 20;
    }

    // Lowercase check
    const hasLower = [...password].some(
        c => c >= 'a' && c <= 'z'
    );
    if (!hasLower) {
        result.isValid = false;
        result.errors.push(`Missing lowercase letter`);
        result.suggestions.push(`Add a lowercase letter`);
    } else {
        result.score += 20;
    }

    // Number check
    const hasNumber = [...password].some(
        c => c >= '0' && c <= '9'
    );
    if (!hasNumber) {
        result.isValid = false;
        result.errors.push(`Missing number`);
        result.suggestions.push(`Add a number`);
    } else {
        result.score += 20;
    }

    // Special character check
    const specials = "!@#$%^&*()_+-=";
    const hasSpecial = [...password].some(
        c => specials.includes(c)
    );
    if (!hasSpecial) {
        result.isValid = false;
        result.errors.push(`Missing special character`);
        result.suggestions.push(`Add a special character`);
    } else {
        result.score += 20;
    }

    // Common password check
    const commonPasswords = ["password", "123456", "qwerty", "admin"];
    if (commonPasswords.includes(password.toLowerCase())) {
        result.isValid = false;
        result.errors.push(`Common password`);
        result.suggestions.push(`Avoid common passwords`);
        result.score -= 20;
    }

    // Normalize score
    result.score = Math.max(0, Math.min(result.score, 100));

    return result;
};

// ---------- TESTING ----------

console.log(`Result for "abc":`, validatePassword('abc'));
console.log(`Result for "MyP@ssw0rd!2024":`, validatePassword('MyP@ssw0rd!2024'));