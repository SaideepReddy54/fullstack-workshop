const memoize = (fn, options = {}) => {
    const cache = new Map();
    const maxSize = options.maxSize ?? Infinity;
    const ttl = options.ttl ?? 0;

    return (...args) => {
        // Use array method (reduce) to build cache key
        const key = args.reduce(
            (acc, curr) => acc + JSON.stringify(curr),
            ""
        );

        const now = Date.now();

        if (cache.has(key)) {
            const entry = cache.get(key);

            if (!ttl || now - entry.time < ttl) {
                // LRU update
                cache.delete(key);
                cache.set(key, entry);
                return entry.value;
            } else {
                cache.delete(key);
            }
        }

        const value = fn(...args);

        // Evict oldest entry if max size reached
        if (cache.size >= maxSize) {
            const oldestKey = cache.keys().next().value;
            cache.delete(oldestKey);
        }

        cache.set(key, { value, time: now });

        return value;
    };
};

// Example function
const expensiveFn = (n) => {
    console.log(`Computing for input: ${n}`);
    return n * n;
};

const memoized = memoize(expensiveFn, { maxSize: 100, ttl: 60000 });

console.log(`Result: ${memoized(5)}`);
console.log(`Result (cached): ${memoized(5)}`);
console.log(`Result: ${memoized(6)}`);