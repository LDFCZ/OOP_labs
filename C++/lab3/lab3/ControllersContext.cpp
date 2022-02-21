#include "ControllersContext.h"

namespace Game {

	ControllersContext::ControllersContext() {
		_controllers = {};
	}

	void ControllersContext::add_new_controller(ControllerName name, Interfaces::Controller* controller) {
		if (_controllers.find(name) != _controllers.end()) {
			throw std::runtime_error(CONSTSPACE::CONTROLLER_ERROR);
		}
		_controllers[name] = controller;
	}

	Interfaces::Controller* ControllersContext::get_controller_by_name(ControllerName name) {
		if (_controllers.find(name) == _controllers.end()) {
			throw std::runtime_error(CONSTSPACE::CONTROLLER_ERROR);
		}
		return _controllers[name];
	}

	std::vector<Interfaces::Controller*> ControllersContext::get_controllers() {
		std::vector<Interfaces::Controller*> ret_value;

		for (std::map<ControllerName, Interfaces::Controller*>::iterator it = _controllers.begin(); it != _controllers.end(); ++it) {
			ret_value.push_back(it->second);

		}
		return ret_value;
	}

	void ControllersContext::set_player1(Interfaces::Player* p1) {
		_p1 = p1;
	}

	void ControllersContext::set_player2(Interfaces::Player* p2) {
		_p2 = p2;
	}

	Interfaces::Player* ControllersContext::get_player1() {
		return _p1;
	}

	Interfaces::Player* ControllersContext::get_player2() {
		return _p2;
	}

	int ControllersContext::get_win_id() {
		return win_id;
	}

	void ControllersContext::set_win_id(int id) {
		win_id = id;
	}

}