#include "PlayerControllersContext.h"
#include <iostream>

namespace Game {

	void PlayerControllersContext::set_player_controllers(Player1Controller* p1_c, Player2Controller* p2_c) {
		_player1 = p1_c;
		_player2 = p2_c;
	}

	void PlayerControllersContext::set_names(std::wstring p1_name, std::wstring p2_name) {
		_player2->set_player_name(p1_name);
		if (_player2_type == Person) {
			_player1->set_player_name(p2_name);
		}
		else {
			_player1->set_player_name(L"SKYNET");
		}
	}

	void PlayerControllersContext::set_battle_fields(std::vector<std::vector<int>> p1_field, std::vector<std::vector<int>> p2_field) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				std::cout << p2_field[i][j] << " ";
			}
			std::cout << std::endl;
		}
		std::cout << std::endl;
		_player1->set_player_field(p1_field);
		_player2->set_player_field(p2_field);
	}

	Game::Player2Type PlayerControllersContext::get_player2_type() const {
		return _player2_type;
	}

	void PlayerControllersContext::set_player2_type(Player2Type type) {
		_player2_type = type;
		_player2->set_player_type(type);
		_player1->set_p2_type(type);
	}

}
