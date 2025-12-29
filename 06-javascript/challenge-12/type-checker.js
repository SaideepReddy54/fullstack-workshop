const typeOf = (value) => {

    if (value === null) return "null";

    if (typeof value === "number" && Number.isNaN(value)) {
        return "nan";
    }

    const baseType = typeof value;

    if (baseType !== "object") {
        return baseType; 
    }

    if (Array.isArray(value)) return "array";
    if (value instanceof Date) return "date";
    if (value instanceof Map) return "map";
    if (value instanceof Set) return "set";
    if (value instanceof RegExp) return "regexp";
    if (value instanceof Error) return "error";
    if (value instanceof Promise) return "promise";

    return "object";
};

console.log(typeOf(null));           // "null"
console.log(typeOf(undefined));      // "undefined"
console.log(typeOf(42));             // "number"
console.log(typeOf(NaN));            // "nan"
console.log(typeOf('hello'));        // "string"
console.log(typeOf(true));           // "boolean"
console.log(typeOf(Symbol()));       // "symbol"
console.log(typeOf([]));             // "array"
console.log(typeOf({}));             // "object"
console.log(typeOf(() => {}));       // "function"
console.log(typeOf(new Date()));     // "date"
console.log(typeOf(new Map()));      // "map"
console.log(typeOf(new Set()));      // "set"
console.log(typeOf(/regex/));        // "regexp"
console.log(typeOf(new Error()));    // "error"
console.log(typeOf(Promise.resolve())); // "promise"