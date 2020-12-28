import React, { Component } from 'react';
import { 
	Checkbox
} from 'react-bootstrap';
import './css/LinhaListagem.css';

class LinhaListagem extends Component {
	constructor(props) {
		super(props);
		this.state = {
			checked: false
		};
	}

	onChangeChecked = (checked) => {
		let isChecked = checked.target.checked;
		this.setState({ checked: isChecked });
		this.notifyChange(isChecked);
	};

	onRowClick = (target, event) => {
		let isChecked = !this.state.checked;
		this.setState({ checked: isChecked });
		this.notifyChange(isChecked);
	};

	notifyChange = (isChecked) => {
		if (!isChecked) {
			this.props.onRegistroDeselect();
			return;
		}
		this.props.onRegistroSelect();
	};

	render() {
		let selectedRow;
		if (this.state.checked) {
			selectedRow = 'selected-row';
		}
		return (
			<tr className={selectedRow} onClick={this.onRowClick}>
				<td><Checkbox className='no-margin' checked={this.state.checked} onChange={this.onChangeChecked}/></td>
				{this.props.children}
			</tr>
		);
	}
}

export default LinhaListagem;