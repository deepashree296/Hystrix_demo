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
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  res.status(500).json({
    data: 'How did you like my server error!',
  });
});

const main = () => {
  app.listen(port);
  log.info(`Listening at http://localhost:${port}`);
};

main();
