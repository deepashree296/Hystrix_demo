const { getBookById, bookDetails } = require('./books');
const Log = require('log');
const express = require('express');
const fs = require('fs');
const bodyParser = require('body-parser');

const app = express();
const port = 3030;
const log = new Log('DEBUG', fs.createWriteStream('app.log', { flags: 'a' }));

let endPoint;

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

// recommendations for the books
endPoint = '/recommended';
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  res.json({
    data: bookDetails,
  });
});


// recommendations for the books with some delay
const delay = 5000; // 5000 miliseconds
endPoint = '/bookid-withdelay';
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  const bookObject = getBookById(req.body.bookId); // Will be null if not a valid book ID
  setTimeout(() => {
    res.json({
      data: bookObject,
    });
  }, delay);
});

// recommendations for the books with no response
endPoint = '/bookid-withnoresponse';
app.post(endPoint, (req) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
});

// recommendations for the books with 5XX error
endPoint = '/bookid-withservererror';
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  res.status(500).json({
    data: 'How did you like my server error!',
  });
});

// endpoint with data validation
endPoint = '/bookid-withvalidation';
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  if (!req.body.bookId) {
    res.status(400).json({
      data: 'Please provide valid book id',
    });
    return;
  }
  const bookObject = getBookById(req.body.bookId); // Will be null if not a valid book ID
  if (bookObject) {
    res.status(200).json({
      data: bookObject,
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
