import RestService from './../../Components/RestService';

class EquipeService extends RestService {

	constructor() {
		super('/equipe');
	}

}

export default EquipeService;