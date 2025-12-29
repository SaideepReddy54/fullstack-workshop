function createLibrary() {
    let books = [];
    let members = [];
    let transactions = [];

    const BORROW_DAYS = 14;

   
    function findBook(isbn) {
        for (let book of books) {
            if (book.isbn === isbn) return book;
        }
        return null;
    }

    function findMember(id) {
        for (let member of members) {
            if (member.id === id) return member;
        }
        return null;
    }

 
    return {
        addBook(book) {
            books.push({ ...book });
        },

        addMember(member) {
            members.push({ ...member });
        },

        borrowBook(memberId, isbn) {
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

        returnBook(memberId, isbn) {
            const book = findBook(isbn);
            if (!book) return false;

            for (let tx of transactions) {
                if (
                    tx.memberId === memberId &&
                    tx.isbn === isbn &&
                    tx.returnedAt === null
                ) {
                    tx.returnedAt = new Date();
                    book.copies++;
                    return true;
                }
            }
            return false;
        },

        getAvailableCopies(isbn) {
            const book = findBook(isbn);
            return book ? book.copies : 0;
        },

        getMemberHistory(memberId) {
            let history = [];

            for (let tx of transactions) {
                if (tx.memberId === memberId) {
                    history.push({ ...tx });
                }
            }
            return history;
        },

        getOverdueBooks() {
            let overdue = [];
            const now = new Date();

            for (let tx of transactions) {
                if (tx.returnedAt === null) {
                    const days =
                        (now - tx.borrowedAt) / (1000 * 60 * 60 * 24);

                    if (days > BORROW_DAYS) {
                        overdue.push({ ...tx });
                    }
                }
            }
            return overdue;
        },

        searchBooks(query) {
            query = query.toLowerCase();
            let results = [];

            for (let book of books) {
                if (
                    book.title.toLowerCase().includes(query) ||
                    book.author.toLowerCase().includes(query)
                ) {
                    results.push({ ...book });
                }
            }
            return results;
        }
    };
}


const library = createLibrary();


library.addBook({ isbn: '123', title: '1984', author: 'Orwell', copies: 3 });
library.addBook({ isbn: '456', title: 'Dune', author: 'Herbert', copies: 2 });

library.addMember({ id: 'M1', name: 'John', email: 'john@example.com' });
library.addMember({ id: 'M2', name: 'Jane', email: 'jane@example.com' });


library.borrowBook('M1', '123');
library.borrowBook('M2', '123');

console.log(library.getAvailableCopies('123')); 

library.returnBook('M1', '123');

console.log(library.getMemberHistory('M1'));


console.log(library.getOverdueBooks()); 
console.log(library.searchBooks('orwell')); 