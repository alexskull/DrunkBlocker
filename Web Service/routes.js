module.exports = function(app) {
	var Trickylock = require('./trickylock');

	//GET
	getPreferences = function(req, res) {
		Trickylock.find(function(err, trickylock) {
			if(!err) res.send(trickylock);
			else console.log('ERROR: ' +err);
		});
	};

	//GET
	getChanges = function(req, res) {
		Trickylock.findById(req.params.id, function(err, trickylock) {
			if(!err) res.send(trickylock);
			else console.log('Error: ' +err);
		});
	};

	//POST
	addTrickylock = function(req, res) {
		console.log('POST');
		console.log(req.body);

		var trickylock = new Trickylock({
			Usuario: req.body.Usuario,
			Funcion_tipo: req.body.Funcion_tipo,
			Funcion_Pref_dia: req.body.Pref_dia,
			//hora: req.body.hora,
			Funcion_Pref_Num_activado: req.body.Funcion_Pref_Num_activado,
			Funcion_Pref_Num_desactivado:	req.body.Funcion_Pref_Num_desactivado

		});

	trickylock.save(function(err) {
	if(!err) console.log('Trickylock Preferences Saved');
	else console.log('ERROR: ' +err);
	});

	res.send(trickylock);

	};

//UPDATE

updateTrickylock = function(req, res) {
	Trickylock.findById(req.params.id, function(err, trickylock) {
	trickylock.Usuario = req.body.Usuario;
	trickylock.Funcion_tipo = req.body.Funcion_tipo;
	trickylock.Funcion_Pref_dia = 	req.body.Funcion_Pref_dia;
	//trickylock.Funcion.Preferencia.hora = 	req.body.Funcion.Preferencia.hora;
	trickylock.Funcion_Pref_Num_activado = 	req.body.Funcion_Pref_Num_activado;
	trickylock.Funcion_Pref_Num_desactivado = req.body.Funcion_Pref_Num_desactivado;

	trickylock.save(function(err) {
		if(!err) console.log('Preferencias Actualizadas');
		else console.log('ERROR: ' +err);
	})

});


};

//DELETE

deleteTrickylock = function(req, res) {
	Trickylock.findById(req.params.id, function(err, trickylock) {
	trickylock.remove(function(err) {
	if(!err) console.log('Preferencias Actualizadas');
	else console.log('ERROR: ' +err);
	})
});
}



// API ROUTES

app.get('/trickylock', getPreferences);
app.get('/trickylock/:id', getChanges);
app.post('/trickylock', addTrickylock);
app.put('/trickylock/:id', updateTrickylock);
app.delete('/trickylock/:id', deleteTrickylock);

}