var mongoose = require('mongoose'),
Schema = mongoose.Schema;

var trickylock = new Schema({
	Usuario: String,
	Funcion_tipo: String,
	Funcion_Pref_dia: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'],
		//Hora: datetime,
	Funcion_Pref_Num_activado: Number,
	Funcion_Pref_Num_desactivado: Number

});

module.exports = mongoose.model('Trickylock', trickylock);

