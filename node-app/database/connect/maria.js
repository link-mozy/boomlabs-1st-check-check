const fs = require('fs');
const path = require('path');
const filePath = path.join(path.join(path.join(__dirname, '../'), '../'), './config/db-info.json');
const db_info = JSON.parse(fs.readFileSync(filePath, 'utf-8'));

const mariadb = require('mysql');

const conn = mariadb.createConnection({
    host: db_info.host,
    port: db_info.port,
    user: db_info.username,
    password: db_info.password,
    database: db_info.database
});

module.exports = conn;