function createLibrary() {
    let books = [];
    let members = [];
    let transactions = [];

    const BORROW_DAYS = 14;

    // Helper functions (arrow functions)
    const findBook = (isbn) =>
        books.find(book => book.isbn === isbn) || null;

    const findMember = (id) =>
        members.find(member => member.id === id) || null;

    return {
        addBook: (book) => {
            books.push({ ...book });
        },

        addMember: (member) => {
            members.push({ ...member });
        },

        borrowBook: (memberId, isbn) => {
            const book = findBook(isbn);
            const member = findMember(memberId);

            if (!book || !member || book.copies <= 0) return false;

            book.copies--;

            transactions.push({
                memberId,
                isbn,
                title: book.title,
                borrowedAt: new Date(),
                returnedAt: null
            });

            return true;
        },

        returnBook: (memberId, isbn) => {
            const book = findBook(isbn);
            if (!book) return false;

            const tx = transactions.find(
                t =>
                    t.memberId === memberId &&
                    t.isbn === isbn &&
                    t.returnedAt === null
            );

            if (!tx) return false;

            tx.returnedAt = new Date();
            book.copies++;
            return true;
        },

        getAvailableCopies: (isbn) =>
            findBook(isbn)?.copies ?? 0,

        getMemberHistory: (memberId) =>
            transactions
                .filter(tx => tx.memberId === memberId)
                .map(tx => ({ ...tx })),

        getOverdueBooks: () => {
            const now = new Date();

            return transactions
                .filter(tx => tx.returnedAt === null)
                .filter(tx => {
                    const days =
                        (now - tx.borrowedAt) / (1000 * 60 * 60 * 24);
                    return days > BORROW_DAYS;
                })
                .map(tx => ({ ...tx }));
        },

        searchBooks: (query) => {
            const q = query.toLowerCase();

            return books
                .filter(
                    book =>
                        book.title.toLowerCase().includes(q) ||
                        book.author.toLowerCase().includes(q)
                )
                .map(book => ({ ...book }));
        }
    };
}

/* ---------- USAGE ---------- */

const library = createLibrary();

library.addBook({ isbn: '123', title: '1984', author: 'Orwell', copies: 3 });
library.addBook({ isbn: '456', title: 'Dune', author: 'Herbert', copies: 2 });

library.addMember({ id: 'M1', name: 'John', email: 'john@example.com' });
library.addMember({ id: 'M2', name: 'Jane', email: 'jane@example.com' });

library.borrowBook('M1', '123');
library.borrowBook('M2', '123');

console.log(`Available copies of 1984: ${library.getAvailableCopies('123')}`);

library.returnBook('M1', '123');

console.log(`Borrow history of M1:`, library.getMemberHistory('M1'));
console.log(`Overdue books:`, library.getOverdueBooks());
console.log(`Search result for "orwell":`, library.searchBooks('orwell'));