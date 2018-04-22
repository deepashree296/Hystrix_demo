const Log = require('log');
const express = require('express');
const fs = require('fs');
const bodyParser = require('body-parser');

const app = express();
const port = 3030;
const log = new Log('DEBUG', fs.createWriteStream('app.log', { flags: 'a' }));

let endPoint;
const userData = [
  {
    userId: 1,
    bookId: [3, 4, 5],
  },
  {
    userId: 2,
    bookId: [6, 7, 8],
  },
];

const getDataForUserId = (userId) => {
  let userDataObject = null;
  userData.some((element) => {
    if (element.userId === userId) {
      userDataObject = element;
      return true;
    }
    return false;
  });
  return userDataObject;
};

const isBookInUserObject = (userDataObject, bookId) => userDataObject.bookId.some((element) => {
  if (element === bookId) {
    return true;
  }
  return false;
});

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

// recommendations for the books
endPoint = '/recommended';
app.get(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  res.json([
    {
      title: 'Godel, Escher, Bach: an Eternal Golden Braid',
      author: 'Douglas Hofstadter',
    },
    {
      title: 'Sapiens: A Brief History of Humankind',
      author: 'Yuval Noah Harari',
    },
    {
      title: 'A Brief History of Time',
      author: 'Stephen Hawking',
    },
  ]);
});


// recommendations for the books with some delay
const delay = 5000; // 5000 miliseconds
endPoint = '/recommended-withdelay';
app.get(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  setTimeout(() => {
    res.json([
      {
        title: 'Godel, Escher, Bach: an Eternal Golden Braid',
        author: 'Douglas Hofstadter',
      },
      {
        title: 'Sapiens: A Brief History of Humankind',
        author: 'Yuval Noah Harari',
      },
      {
        title: 'A Brief History of Time',
        author: 'Stephen Hawking',
      },
    ]);
  }, delay);
});

// recommendations for the books with no response
endPoint = '/recommended-withnoresponse';
app.get(endPoint, (req) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
});

// recommendations for the books with 5XX error
endPoint = '/recommended-withservererror';
app.get(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  res.status(500).json({
    data: 'How did you like my server error!',
  });
});

// endpoint with data validation
endPoint = '/recommended-withvalidation';
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  if (!req.body.bookId || !req.body.userId) {
    res.status(400).json({
      data: 'Please provide valid book id and user id',
    });
    return;
  }
  const userId = parseInt(req.body.userId, 10);
  const bookId = parseInt(req.body.bookId, 10);
  const userDataObject = getDataForUserId(userId);
  const foundBookId = isBookInUserObject(userDataObject, bookId);
  if (foundBookId) {
    res.status(200).json({
      data: `You asked for details of bookId: ${req.body.bookId}, which we found`,
    });
  } else {
    res.status(400).json({
      data: `You asked for details of bookId: ${req.body.bookId}, which we could not find`,
    });
  }
});


const main = () => {
  app.listen(port);
  log.info(`Listening at http://localhost:${port}`);
};

main();
