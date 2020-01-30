$("#form_cliente").validate({
	rules : {
		nome:{
			required:true,
			minlength:3
		},
		emailCliente:{
			required:true
		},
		cpf:{
			required:true
			verificaCPF: true
		},
		dataNascimento:{
			required:true
		},
		password:{
			required:true
		},
		telefone:{
			required:true
		},
		"endereco.logadouro":{
			required:true
		},
		"endereco.numero":{
			required:true
		},
		"endereco.bairro":{
			required:true
		},
		"endereco.cidade":{
			required:true
		},
		"endereco.estado":{
			required:true
		},
		"endereco.cep":{
			required:true
		}
	},
	messages:{
		nome:{
			required:"Por favor, informe seu nome",
			minlength:"O nome deve ter pelo menos 3 caracteres"
		},
		emailCliente:{
			required:"É necessário informar um email"
		},
		cpf:{
			required:"O CPF não pode ficar em branco"
			verificaCPF: "CPF inválido"
		},
		dataNascimento:{
			required:"Informe uma data de nascimento válida"
		},
		password:{
			required:"Digite uma senha válida"
		},
		telefone:{
			required:"Digite um telefone de contato"
		},
		"endereco.logadouro":{
			required:"Digite um logradouro"
		},
		"endereco.numero":{
			required:"Digite o numero do local"
		},
		"endereco.bairro":{
			required:"Digite o nome do bairro"
		},
		"endereco.cidade":{
			required:"Digite o nome da cidade"
		},
		"endereco.estado":{
			required:"Digite o nome do estado"
		},
		"endereco.cep":{
			required:"Digite um CEP"
		}
	}
});