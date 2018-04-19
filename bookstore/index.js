const Log = require('log');
const express = require('express');
const fs = require('fs');
const bodyParser = require('body-parser');

const app = express();
const port = 3030;
const log = new Log('DEBUG', fs.createWriteStream('app.log', { flags: 'a' }));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

// recommendations for the books
const endPoint = '/recommended';
app.post(endPoint, (req, res) => {
  log.info(`POST ${endPoint} - Data - ${JSON.stringify(req.body)}`);
  res.json([
    {
      title: 'Godel, Escher, Bach: an Eternal Golden Braid',
      author: 'Douglas Hofstadter'
    },
    {
      title: 'Sapiens: A Brief History of Humankind',
      author: 'Yuval Noah Harari'
    },
    {
      title: 'A Brief History of Time',
      author: 'Stephen Hawking'
    }
  ]);
});


const main = () => {
  app.listen(port);
  log.info(`Listening at http://localhost:${port}`);
};

main();
