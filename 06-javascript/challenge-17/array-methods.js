function myMap(arr, callback) {
    let result = [];
    for (let i = 0; i < arr.length; i++) {
        result[result.length] = callback(arr[i], i, arr);
    }
    return result;
}

function myFilter(arr, callback) {
    let result = [];
    for (let i = 0; i < arr.length; i++) {
        if (callback(arr[i], i, arr)) {
            result[result.length] = arr[i];
        }
    }
    return result;
}

function myReduce(arr, callback, initialValue) {
    let accumulator = initialValue;
    let startIndex = 0;

    if (accumulator === undefined) {
        accumulator = arr[0];
        startIndex = 1;
    }

    for (let i = startIndex; i < arr.length; i++) {
        accumulator = callback(accumulator, arr[i], i, arr);
    }
    return accumulator;
}

function myFind(arr, callback) {
    for (let i = 0; i < arr.length; i++) {
        if (callback(arr[i], i, arr)) {
            return arr[i];
        }
    }
    return undefined;
}

function myEvery(arr, callback) {
    for (let i = 0; i < arr.length; i++) {
        if (!callback(arr[i], i, arr)) {
            return false;
        }
    }
    return true;
}

function mySome(arr, callback) {
    for (let i = 0; i < arr.length; i++) {
        if (callback(arr[i], i, arr)) {
            return true;
        }
    }
    return false;
}

function myFlat(arr, depth = 1) {
    let result = [];

    for (let i = 0; i < arr.length; i++) {
        if (Array.isArray(arr[i]) && depth > 0) {
            let flattened = myFlat(arr[i], depth - 1);
            for (let j = 0; j < flattened.length; j++) {
                result[result.length] = flattened[j];
            }
        } else {
            result[result.length] = arr[i];
        }
    }
    return result;
}


console.log(myMap([1, 2, 3], x => x * 2));


console.log(myFilter([1, 2, 3, 4], x => x > 2));


console.log(myReduce([1, 2, 3], (a, b) => a + b, 0));


console.log(myFind([1, 2, 3], x => x > 1));


console.log(myFlat([1, [2, [3, [4]]]], 2));
