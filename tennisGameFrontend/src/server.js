const express = require('express');
const cors = require('cors');

const app = express();

// CORS middleware — add before routes
app.use(cors({
    origin: 'http://localhost:3000',
    methods: ['GET', 'POST', 'OPTIONS'],
    allowedHeaders: ['Content-Type']
}));

// Your routes
app.get('/api/tennis/play', (req, res) => {
    res.send('Let’s play some tennis!');
});

app.listen(8080, () => {
    console.log('Server is running on http://localhost:8080');
});
