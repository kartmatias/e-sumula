import React, { Component } from 'react';
import {
	FormGroup,
	ControlLabel,
	FormControl,
	Button,
	HelpBlock
} from 'react-bootstrap';
import SexoToggle from './../../Components/SexoToggle.jsx';
import PessoaService from './PessoaService.jsx';
import EquipeService from './../Equipe/EquipeService';
import './Pessoa.css';

class FormularioPessoa extends Component {
	constructor(props) {
		super(props);
		this.state = {
			nome: '',
			rg: '',
			dataNascimento: '',
			sexo: 'M',
			email: '',
			telefone: '',
			endereco: '',
			equipe: '',
			emailValido: true,
			equipes: []
		};
		if (props.id) {
			let service = new PessoaService();
			service.getById(props.id).then(this.setPessoa);
		}
		let equipeService = new EquipeService();
		equipeService.findAll().then((allEquipes) => {
			let equipes = allEquipes._embedded.equipe.map((equipe) => {
				return {
					nome: equipe.nome,
					link: equipe._links.self.href
				};
			});
			this.setState({ equipes: equipes });
		});
	}

	setPessoa = (pessoa) => {
		this.setState({
			id: pessoa.id,
			nome: pessoa.nome,
			rg: pessoa.rg,
			dataNascimento: pessoa.dataNascimento,
			sexo: pessoa.sexo,
			email: pessoa.email,
			telefone: pessoa.telefone,
			endereco: pessoa.endereco,
			emailValido: true
		});
		fetch(pessoa._links.equipe.href).then((response) => {
			return response.json();
		}).then((equipe) => {
			console.log(equipe);
			this.setState({ equipe: equipe._links.self.href });
		});
	};

	save = () => {
		if (this.cadastroValido()){
			let service = new PessoaService();
			service.post(this.state).then(() => {
				this.props.onTelaChange('listagem');
			});
		}
	};

	cadastroValido = () => {
		let valido = true;
		if (!this.validaEmail(this.state.email)) {
			valido = false;
			this.setState({ emailValido: false });
		}
		return valido;
	};

	validaEmail = (email) => {
		let emailPrefixoSufixo = email.split('@');
		if (emailPrefixoSufixo.length !== 2) {
			return false;
		}
		return emailPrefixoSufixo[0].length > 0 && emailPrefixoSufixo[1].length > 0;
	};

	onChangeEmail = (email) => {
		let textEmail = email.target.value;
		this.setState({ email: textEmail });
		if (!this.state.emailValido && this.validaEmail(textEmail)) {
			this.setState({ emailValido: true });
		}
	};

	render() {
		let emailInvalido;
		if (!this.state.emailValido) {
			emailInvalido = <HelpBlock>E-mail inválido</HelpBlock>
		}
		let equipes = this.state.equipes.map((equipe) => {
			return <option key={equipe.link} value={equipe.link}>{equipe.nome}</option>;
		});
		equipes.unshift(<option key="" value=""></option>);
		return (
			<form>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Nome</ControlLabel>
							<FormControl
								type="text"
								value={this.state.nome}
								onChange={(nome) => {this.setState({ nome: nome.target.value });}}
							/>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12 col-sm-6">
						<FormGroup>
							<ControlLabel>RG</ControlLabel>
							<FormControl
								type="text"
								value={this.state.rg}
								onChange={(rg) => {this.setState({ rg: rg.target.value });}}
							/>
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-3">
						<FormGroup>
							<ControlLabel>Data de nascimento</ControlLabel>
							<FormControl
								type="date"
								value={this.state.dataNascimento}
								onChange={(dataNascimento) => {this.setState({ dataNascimento: dataNascimento.target.value });}}
							/>
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-1">
						<FormGroup>
							<ControlLabel>Sexo</ControlLabel>
							<div>
								<SexoToggle
									value={this.state.sexo}
									onChange={(selected) => {this.setState({ sexo: selected ? 'M' : 'F' });	}}
								/>
							</div>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12 col-sm-6">
						<FormGroup validationState={this.state.emailValido ? null : 'error'}>
							<ControlLabel>E-mail</ControlLabel>
							<FormControl
								type="email"
								value={this.state.email}
								onChange={this.onChangeEmail}
							/>
							{emailInvalido}
							<FormControl.Feedback />
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-6">
						<FormGroup>
							<ControlLabel>Telefone</ControlLabel>
							<FormControl
								type="tel"
								value={this.state.telefone}
								onChange={(telefone) => {this.setState({ telefone: telefone.target.value });}}
							/>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Endereço</ControlLabel>
							<FormControl
								type="text"
								value={this.state.endereco}
								onChange={(endereco) => {this.setState({ endereco: endereco.target.value });}}
							/>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Equipe</ControlLabel>
							<FormControl
								componentClass="select"
								value={this.state.equipe}
								onChange={(equipe) => {this.setState({ equipe: equipe.target.value });}}
							>
								{equipes}
							</FormControl>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12">
						<div className="pull-right">
							<Button bsStyle="default" className="btn-margin-right" onClick={() => {this.props.onTelaChange('listagem');}}>Cancelar</Button>
							<Button bsStyle="primary" onClick={this.save}>Salvar</Button>
						</div>
					</div>
				</div>
			</form>
		);
	}
}

export default FormularioPessoa;