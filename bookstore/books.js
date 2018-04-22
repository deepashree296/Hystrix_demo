const bookDetails = [
  {
    bookId: 'B1',
    bookName: 'Sapiens',
    bookAuthor: 'Yuval Noah Harari',
    bookCategory: 'History',
    bookPrice: '250',
    yearOfRelease: '2010',
  },
  {
    bookId: 'B2',
    bookName: 'This is your brain on music',
    bookAuthor: 'Daniel J',
    bookCategory: 'Music',
    bookPrice: '390',
    yearOfRelease: '2006',
  },
  {
    bookId: 'B3',
    bookName: 'Autobiography of a Yogi',
    bookAuthor: 'Paramhansa Yogananda',
    bookCategory: 'Spirituality',
    bookPrice: '200',
    yearOfRelease: '2010',
  },
  {
    bookId: 'B4',
    bookName: 'Into The Wild',
    bookAuthor: '',
    bookCategory: 'Travel',
    bookPrice: '250',
    yearOfRelease: '2010',
  },
];

const getBookById = (bookId) => {
  let bookObject = null;
  bookDetails.some((element) => {
    if (element.bookId === bookId) {
      bookObject = element;
      return true;
    }
    return false;
  });
  return bookObject;
};

module.exports = {
  getBookById,
  bookDetails,
};
