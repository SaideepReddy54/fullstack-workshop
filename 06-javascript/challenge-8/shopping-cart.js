function createShoppingCart() {
    let items = [];
    let discount = 0;

    function findItemIndex(id) {
        for (let i = 0; i < items.length; i++) {
            if (items[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    return {
        addItem(product) {
            const index = findItemIndex(product.id);

            if (index !== -1) {
               
                items[index].quantity += product.quantity;
            } else {
              
                items.push({ ...product });
            }
        },

        updateQuantity(id, quantity) {
            const index = findItemIndex(id);
            if (index !== -1) {
                items[index].quantity = quantity;
            }
        },

        removeItem(id) {
            const index = findItemIndex(id);
            if (index !== -1) {
                items.splice(index, 1);
            }
        },

        getItems() {
          
            return items.map(item => ({ ...item }));
        },

        getTotal() {
            let total = 0;

            for (let item of items) {
                total += item.price * item.quantity;
            }

            if (discount > 0) {
                total = total - (total * discount / 100);
            }

            return Number(total.toFixed(2));
        },

        getItemCount() {
            let count = 0;
            for (let item of items) {
                count += item.quantity;
            }
            return count;
        },

        isEmpty() {
            return items.length === 0;
        },

        applyDiscount(code, percent) {
            if (typeof percent === 'number' && percent > 0) {
                discount = percent;
            }
        },

        clear() {
            items = [];
            discount = 0;
        }
    };
}


const cart = createShoppingCart();

cart.addItem({ id: 1, name: 'Laptop', price: 999, quantity: 1 });
cart.addItem({ id: 2, name: 'Mouse', price: 29, quantity: 2 });
cart.addItem({ id: 1, name: 'Laptop', price: 999, quantity: 1 });

console.log(cart.getItems());


cart.updateQuantity(1, 3);
cart.removeItem(2);

console.log(cart.getTotal());    
console.log(cart.getItemCount()); 
console.log(cart.isEmpty());      

cart.applyDiscount('SAVE10', 10);
console.log(cart.getTotal());     

cart.clear();
console.log(cart.isEmpty());      