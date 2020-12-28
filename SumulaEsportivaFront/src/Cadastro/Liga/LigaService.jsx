import RestService from './../../Components/RestService';

class LigaService extends RestService {

	constructor() {
		super('/liga');
	}

}

export default LigaService;