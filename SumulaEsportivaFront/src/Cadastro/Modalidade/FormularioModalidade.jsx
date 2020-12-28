import React, { Component } from 'react';
import {
	FormGroup,
	ControlLabel,
	FormControl,
	Button
} from 'react-bootstrap';
import FontAwesome from 'react-fontawesome';
import Service from './ModalidadeService.jsx';

class FormularioModalidade extends Component {
	constructor(props) {
		super(props);
		this.state = {
			descricao: '',
			quantidadePeriodos: '',
			duracaoPeriodos: '',
			quantidadeTemposExtras: '',
			duracaoExtra: '',
			quantidadeTimeout: '',
			duracaoTimeout: '',
			tiposPunicao: [],
			tiposPonto: [],
			descricaoPonto: '',
			valorPonto: '',
			tipoPunicao: '',
			descricaoPunicao: ''
		};
		if (props.id) {
			let service = new Service();
			service.getById(props.id).then(this.setEntity);
		}
	}

	setEntity = (entity) => {
		this.setState({
			id: entity.id,
			descricao: entity.descricao,
			quantidadePeriodos: entity.tempo.quantidadePeriodos,
			duracaoPeriodos: entity.tempo.duracaoPeriodos,
			quantidadeTemposExtras: entity.tempo.quantidadeTemposExtras,
			duracaoExtra: entity.tempo.duracaoExtra,
			quantidadeTimeout: entity.tempo.quantidadeTimeout,
			duracaoTimeout: entity.tempo.duracaoTimeout,
			tiposPonto: entity.tiposPonto,
			tiposPunicao: entity.tiposPunicao
		});
	};

	save = () => {
		let service = new Service();
		let entity = {
			descricao: this.state.descricao,
			tempo: {
				quantidadePeriodos: this.state.quantidadePeriodos,
				duracaoPeriodos: this.state.duracaoPeriodos,
				quantidadeTemposExtras: this.state.quantidadeTemposExtras,
				duracaoExtra: this.state.duracaoExtra,
				quantidadeTimeout: this.state.quantidadeTimeout,
				duracaoTimeout: this.state.duracaoTimeout
			},
			tiposPonto: this.state.tiposPonto,
			tiposPunicao: this.state.tiposPunicao
		}
		if (this.state.id) {
			entity.id = this.state.id;
			entity.tempo.id = this.state.id;
		}
		service.post(entity).then(() => {
			this.props.onTelaChange('listagem');
		});
	};

	adicionarPonto = () => {
		let pontos = this.state.tiposPonto;
		pontos.push({
			descricao: this.state.descricaoPonto,
			valor: this.state.valorPonto
		});
		this.setState({
			tiposPonto: pontos,
			descricaoPonto: '',
			valorPonto: ''
		});
	};

	removerPonto = (tipoPonto) => {
		let pontos = this.state.tiposPonto;
		let index = pontos.indexOf(tipoPonto);
		pontos.splice(index, 1);
		this.setState({ tiposPonto: pontos });
	};

	adicionarPunicao = () => {
		let punicoes = this.state.tiposPunicao;
		punicoes.push({
			tipo: this.state.tipoPunicao,
			descricao: this.state.descricaoPunicao
		});
		this.setState({
			tiposPunicao: punicoes,
			tipoPunicao: '',
			descricaoPunicao: ''
		});
	};

	removerPunicao = (tipoPunicao) => {
		let punicoes = this.state.tiposPunicao;
		let index = punicoes.indexOf(tipoPunicao);
		punicoes.splice(index, 1);
		this.setState({ tiposPunicao: punicoes });
	};

	render() {
		let key = 1;
		let pontos = this.state.tiposPonto.map((tipoPonto) => {
			return (
				<tr key={key++} className="background-white">
					<td>{tipoPonto.descricao}</td>
					<td>{tipoPonto.valor}</td>
					<td className="centralized">
						<Button bsStyle="danger" onClick={() => this.removerPonto(tipoPonto)}>
							<FontAwesome name="times"/>
						</Button>
					</td>
				</tr>
			);
		});
		let punicoes = this.state.tiposPunicao.map((tipoPunicao) => {
			return (
				<tr key={key++} className="background-white">
					<td>{tipoPunicao.tipo}</td>
					<td>{tipoPunicao.descricao}</td>
					<td className="centralized">
						<Button bsStyle="danger" onClick={() => this.removerPunicao(tipoPunicao)}>
							<FontAwesome name="times"/>
						</Button>
					</td>
				</tr>
			);
		});
		return (
			<form>
				<div className="row">
					<div className="col-xs-12">
						<FormGroup>
							<ControlLabel>Nome e modalidade do esporte</ControlLabel>
							<FormControl
								type="text"
								value={this.state.descricao}
								onChange={(descricao) => {this.setState({ descricao: descricao.target.value });}}
							/>
						</FormGroup>
					</div>
				</div>
				<div className="row">
					<fieldset className="col-xs-12 col-sm-6">
						<legend>Períodos:</legend>
						<div className="row">
							<div className="col-xs-6">
								<FormGroup>
									<ControlLabel>Quantidade</ControlLabel>
									<FormControl
										type="number"
										value={this.state.quantidadePeriodos}
										onChange={(quantidadePeriodos) => {this.setState({ quantidadePeriodos: quantidadePeriodos.target.value });}}
									/>
								</FormGroup>
							</div>
							<div className="col-xs-6">
								<FormGroup>
									<ControlLabel>Duração</ControlLabel>
									<FormControl
										type="number"
										value={this.state.duracaoPeriodos}
										onChange={(duracaoPeriodos) => {this.setState({ duracaoPeriodos: duracaoPeriodos.target.value });}}
									/>
								</FormGroup>
							</div>
						</div>
					</fieldset>
					<fieldset className="col-xs-12 col-sm-6">
						<legend>Tempos extra:</legend>
						<div className="row">
							<div className="col-xs-6">
								<FormGroup>
									<ControlLabel>Quantidade</ControlLabel>
									<FormControl
										type="number"
										value={this.state.quantidadeTemposExtras}
										onChange={(quantidadeTemposExtras) => {this.setState({ quantidadeTemposExtras: quantidadeTemposExtras.target.value });}}
									/>
								</FormGroup>
							</div>
							<div className="col-xs-6">
								<FormGroup>
									<ControlLabel>Duração</ControlLabel>
									<FormControl
										type="number"
										value={this.state.duracaoExtra}
										onChange={(duracaoExtra) => {this.setState({ duracaoExtra: duracaoExtra.target.value });}}
									/>
								</FormGroup>
							</div>
						</div>
					</fieldset>
				</div>
				<div className="row">
					<fieldset className="col-xs-12 col-sm-6">
						<legend>Timeout:</legend>
						<div className="row">
							<div className="col-xs-6">
								<FormGroup>
									<ControlLabel>Quantidade</ControlLabel>
									<FormControl
										type="number"
										value={this.state.quantidadeTimeout}
										onChange={(quantidadeTimeout) => {this.setState({ quantidadeTimeout: quantidadeTimeout.target.value });}}
									/>
								</FormGroup>
							</div>
							<div className="col-xs-6">
								<FormGroup>
									<ControlLabel>Duração</ControlLabel>
									<FormControl
										type="number"
										value={this.state.duracaoTimeout}
										onChange={(duracaoTimeout) => {this.setState({ duracaoTimeout: duracaoTimeout.target.value });}}
									/>
								</FormGroup>
							</div>
						</div>
					</fieldset>
				</div>
				<h4>Tipos de ponto</h4>
				<div className="row">
					<div className="col-xs-12 col-sm-6">
						<FormGroup>
							<ControlLabel>Nome do tipo de ponto</ControlLabel>
							<FormControl
								value={this.state.descricaoPonto}
								onChange={(descricaoPonto) => {this.setState({ descricaoPonto: descricaoPonto.target.value });}}
							/>
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-4">
						<FormGroup>
							<ControlLabel>Valor do ponto</ControlLabel>
							<FormControl
								type="number"
								value={this.state.valorPonto}
								onChange={(valorPonto) => {this.setState({ valorPonto: valorPonto.target.value });}}
							/>
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-2">
						<FormGroup>
							<ControlLabel className="invisible">.</ControlLabel>
							<Button bsStyle="primary" className="btn-block" onClick={this.adicionarPonto}>Adicionar</Button>
						</FormGroup>
					</div>
				</div>
				<hr/>
				<div className="row">
					<div className="col-xs-12">
						<ControlLabel>Pontos cadastrados</ControlLabel>
						<table className="table table-striped table-bordered">
							<thead>
								<tr>
									<th className="col-xs-6">Nome</th>
									<th className="col-xs-5">Valor</th>
									<th className="col-xs-1 centralized">Ações</th>
								</tr>
							</thead>
							<tbody>
								{pontos}
							</tbody>
						</table>
					</div>
				</div>
				<h4>Tipos de punição</h4>
				<div className="row">
					<div className="col-xs-12 col-sm-4">
						<FormGroup>
							<ControlLabel>Nome do tipo de punição</ControlLabel>
							<FormControl
								value={this.state.tipoPunicao}
								onChange={(tipoPunicao) => {this.setState({ tipoPunicao: tipoPunicao.target.value });}}
							/>
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-6">
						<FormGroup>
							<ControlLabel>Descrição do tipo de punição</ControlLabel>
							<FormControl
								value={this.state.descricaoPunicao}
								onChange={(descricaoPunicao) => {this.setState({ descricaoPunicao: descricaoPunicao.target.value });}}
							/>
						</FormGroup>
					</div>
					<div className="col-xs-12 col-sm-2">
						<FormGroup>
							<ControlLabel className="invisible">.</ControlLabel>
							<Button bsStyle="primary" className="btn-block" onClick={this.adicionarPunicao}>Adicionar</Button>
						</FormGroup>
					</div>
				</div>
				<hr/>
				<div className="row">
					<div className="col-xs-12">
						<ControlLabel>Punições cadastradas</ControlLabel>
						<table className="table table-striped table-bordered">
							<thead>
								<tr>
									<th className="col-xs-6">Nome</th>
									<th className="col-xs-5">Descrição</th>
									<th className="col-xs-1 centralized">Ações</th>
								</tr>
							</thead>
							<tbody>
								{punicoes}
							</tbody>
						</table>
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

export default FormularioModalidade;