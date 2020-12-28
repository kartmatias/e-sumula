import React, { Component } from 'react';
import {
	FormGroup,
	ControlLabel,
	FormControl,
	Button
} from 'react-bootstrap';
import ModalidadeService from './../Modalidade/ModalidadeService';
import LigaService from './LigaService.jsx';
import EquipeService from './../Equipe/EquipeService';
import './Liga.css';

class FormularioLiga extends Component {
	constructor(props) {
		super(props);
		this.state = {
			nome: '',
			data: '',
			cabecaDeChave: '',
			equipes: [],
			modalidade: '',
			modalidades: []
		};
		if (props.id) {
			let service = new LigaService();
			service.getById(props.id).then(this.setLiga);
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

		let modalidadeService = new ModalidadeService();
		modalidadeService.findAll().then((allModalidades) => {
			let modalidades = allModalidades._embedded.modalidade.map((modalidade) => {
				return {
					nome: modalidade.descricao,
					link: modalidade._links.self.href
				};
			});
			this.setState({ modalidades: modalidades });
		});

	}

	setLiga = (liga) => {
		this.setState({
			id: liga.id,
			nome: liga.nome,
			cabecaDeChave: liga.cabecaDeChave,
			data: liga.data
		});

		fetch(liga._links.cabecaDeChave.href).then((response) => {
			return response.json();
		}).then((cabecaDeChave) => {
			this.setState({ cabecaDeChave: cabecaDeChave._links.self.href });
		});

		fetch(liga._links.modalidade.href).then((response) => {
			return response.json();
		}).then((modalidade) => {
			this.setState({ modalidade: modalidade._links.self.href });
		});
	};

	save = () => {
		if (this.cadastroValido()) {
			let service = new LigaService();
			service.post(this.state).then(() => {
				this.props.onTelaChange('listagem');
			});
		}
	};

	cadastroValido = () => {
		let valido = true;
		return valido;
	};

	onChangeEmail = (email) => {
		let textEmail = email.target.value;
		this.setState({ email: textEmail });
	};

	render() {
		let equipes = this.state.equipes.map((cabecaDeChave) => {
			return <option key={cabecaDeChave.link} value={cabecaDeChave.link}>{cabecaDeChave.nome}</option>;
		});
		let modalidades = this.state.modalidades.map(modalidade => {
			return <option key={modalidade.link} value={modalidade.link}>{modalidade.nome}</option>;
		})

		equipes.unshift(<option key="" value=""></option>);
		modalidades.unshift(<option key="" value=""></option>);
		
		return (
			<form>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Nome</ControlLabel>
							<FormControl
								type="text"
								value={this.state.nome}
								onChange={(nome) => { this.setState({ nome: nome.target.value }); }}
							/>
						</FormGroup>
					</div>
				</div>
				 <div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Modalidade</ControlLabel>
							<FormControl
								componentClass="select"
								value={this.state.modalidade}
								onChange={(modalidade) => { this.setState({ modalidade: modalidade.target.value }); }}
							>
								{modalidades}
							</FormControl>
						</FormGroup>
					</div>
				</div> 
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Cabeça de Chave</ControlLabel>
							<FormControl
								componentClass="select"
								value={this.state.cabecaDeChave}
								onChange={(cabecaDeChave) => { this.setState({ cabecaDeChave: cabecaDeChave.target.value }); }}
							>
								{equipes}
							</FormControl>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12 col-sm-3">
						<FormGroup>
							<ControlLabel>Data</ControlLabel>
							<FormControl
								type="datetime-local"
								value={this.state.data}
								onChange={(data) => { this.setState({ data: data.target.value }); }}
							/>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12">
						<div className="pull-right">
							<Button bsStyle="default" className="btn-margin-right" onClick={() => { this.props.onTelaChange('listagem'); }}>Cancelar</Button>
							<Button bsStyle="primary" onClick={this.save}>Salvar</Button>
						</div>
					</div>
				</div>
			</form>
		);
	}
}

export default FormularioLiga;