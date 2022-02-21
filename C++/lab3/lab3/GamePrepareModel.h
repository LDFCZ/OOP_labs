#pragma once

#include <vector>

#include "Ship.h"

namespace Game {

	class GamePrepareModel {
	private:
		std::vector<std::vector<int>> _field;

		std::vector<std::vector<std::pair<int, int>>> possible_places;

		bool _check_conclusion(Ship*, int, int);

		void _circle_ship(Ship*, int, int);

		void _uncircle_ship(Ship*, int, int);
	public:
		GamePrepareModel();

		std::pair<int, int> get_right_position(Ship* ,float, float);

		void set_ship_on_field(Ship*);

		void remove_ship_from_field(Ship*);

		void print_field();

		void clear_field();

		std::vector<std::vector<int>> get_field();
	};
}