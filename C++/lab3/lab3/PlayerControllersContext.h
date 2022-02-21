#pragma once

#include <string>
#include <vector>

#include "Player1Controller.h"
#include "Player2Controller.h"

namespace Game {

	class PlayerControllersContext {
	private:
		Player1Controller* _player1;
		Player2Controller* _player2;

		Player2Type _player2_type;
	public:

		void set_player_controllers(Player1Controller*, Player2Controller*);

		void set_names(std::wstring, std::wstring = L"");
		void set_battle_fields(std::vector<std::vector<int>>, std::vector<std::vector<int>>);

		Player2Type get_player2_type() const;

		void set_player2_type(Player2Type);
	};
}

