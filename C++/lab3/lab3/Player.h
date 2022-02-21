#pragma once

#include "Ship.h"

namespace Game {
	enum MoveType {Miss, Hit, Kill, EndGame};
}

namespace Interfaces {

	class Player {
	public:
		virtual Game::MoveType get_enemy_move(const int, const int) = 0;

		virtual Game::Ship* get_last_died_ship() = 0;
	};
}

