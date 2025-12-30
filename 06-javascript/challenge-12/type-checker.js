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

/* ---------- TEST CASES USING ARRAY METHOD ---------- */

const testValues = [
    null,
    undefined,
    42,
    NaN,
    "hello",
    true,
    Symbol(),
    [],
    {},
    () => {},
    new Date(),
    new Map(),
    new Set(),
    /regex/,
    new Error(),
    Promise.resolve()
];

testValues.forEach(value => {
    console.log(`Type of value is: ${typeOf(value)}`);
});