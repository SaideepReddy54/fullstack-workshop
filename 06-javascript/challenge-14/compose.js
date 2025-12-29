
const compose = (...fns) => {
    return function (value) {
        let result = value;

        for (let i = fns.length - 1; i >= 0; i--) {
            result = fns[i](result);
        }

        return result;
    };
};

const pipe = (...fns) => {
    return function (value) {
        let result = value;

        for (let i = 0; i < fns.length; i++) {
            result = fns[i](result);
        }

        return result;
    };
};

const addOne = x => x + 1;
const double = x => x * 2;
const square = x => x * x;

const composed = compose(addOne, double, square);
console.log(composed(3));


const piped = pipe(square, double, addOne);
console.log(piped(3));
