import React, { Component } from 'react';

class GenericList extends Component {
	render() {
		return (
			<div>
				<table className="table table-striped table-hover table-bordered">
					<thead>
						{this.props.header}
					</thead>
					<tbody>
						{this.props.listagemRegistros}
					</tbody>
				</table>
			</div>
		);
	}
}

export default GenericList;