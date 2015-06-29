var express=require('express');
var app=express();
var bodyParser = require('body-parser');
methodOverride = require("method-override");
var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost/trickylock', function(err, res) {if(err) console.log('Error: Conectando a la Base de Datos: ' + err); else console.log('Conexion a la BD realizada')});

app.use(bodyParser.urlencoded({ extended: false}));
app.use(bodyParser.json());
app.use(methodOverride());

var router = express.Router();

router.get('/', function(req, res) {
	res.send("Hello World");
});

require('./routes')(app);
app.use(router);

app.listen(3000, function() {
	console.log("Node server running on http://localhost:3000");
});