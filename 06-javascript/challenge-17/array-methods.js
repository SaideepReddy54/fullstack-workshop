// Custom map
const myMap = (arr, callback) => {
    const result = [];
    arr.forEach((item, index) => {
        result.push(callback(item, index, arr));
    });
    return result;
};

// Custom filter
const myFilter = (arr, callback) => {
    const result = [];
    arr.forEach((item, index) => {
        if (callback(item, index, arr)) {
            result.push(item);
        }
    });
    return result;
};

// Custom reduce
const myReduce = (arr, callback, initialValue) => {
    const hasInitial = initialValue !== undefined;
    return arr.reduce(
        (acc, curr, index) =>
            hasInitial || index > 0 ? callback(acc, curr, index, arr) : curr,
        hasInitial ? initialValue : undefined
    );
};

// Custom find
const myFind = (arr, callback) => {
    for (let i = 0; i < arr.length; i++) {
        if (callback(arr[i], i, arr)) return arr[i];
    }
    return undefined;
};

// Custom every
const myEvery = (arr, callback) =>
    arr.reduce((acc, curr, index) => acc && callback(curr, index, arr), true);

// Custom some
const mySome = (arr, callback) =>
    arr.reduce((acc, curr, index) => acc || callback(curr, index, arr), false);

// Custom flat
const myFlat = (arr, depth = 1) =>
    depth > 0
        ? arr.reduce(
              (acc, curr) =>
                  acc.concat(
                      Array.isArray(curr) ? myFlat(curr, depth - 1) : curr
                  ),
              []
          )
        : arr.slice();

/* ---------- TESTING ---------- */

console.log(`myMap: ${JSON.stringify(myMap([1, 2, 3], x => x * 2))}`);
console.log(`myFilter: ${JSON.stringify(myFilter([1, 2, 3, 4], x => x > 2))}`);
console.log(`myReduce: ${myReduce([1, 2, 3], (a, b) => a + b, 0)}`);
console.log(`myFind: ${myFind([1, 2, 3], x => x > 1)}`);
console.log(`myFlat: ${JSON.stringify(myFlat([1, [2, [3, [4]]]], 2))}`);